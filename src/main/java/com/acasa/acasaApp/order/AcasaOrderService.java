package com.acasa.acasaApp.order;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.cart.Cart;
import com.acasa.acasaApp.cart.CartService;
import com.acasa.acasaApp.cartitem.CartItem;
import com.acasa.acasaApp.orderitem.OrderItem;
import com.acasa.acasaApp.orderitem.OrderItemService;

@Service
public class AcasaOrderService {

	private final AcasaOrderRepo orderRepo;
	private final OrderItemService orderItemService;
	private final CartService cartService;

	@Autowired
	public AcasaOrderService(AcasaOrderRepo orderRepo, OrderItemService orderItemService, CartService cartService) {
		super();
		this.orderRepo = orderRepo;
		this.orderItemService = orderItemService;
		this.cartService = cartService;
	}
	
	public List<AcasaOrder> getAllOrders(){
		return orderRepo.findAll();
	}
	
	

	public AcasaOrder getOrderById(Long orderId) throws NotFoundException {
		return orderRepo.findById(orderId).orElseThrow(NotFoundException:: new);
	}
	
	public List<AcasaOrder> getOrderByUserId(Long userId) {
		return orderRepo.findAll()
				.stream()
				.filter(order->order.getAppUser().getUserId().equals(userId))
				.collect(Collectors.toList());
	}
	
	public AcasaOrder placeOrder(List<CartItem> cartItemList, Long cartId) throws ServerException, NotFoundException {
		AcasaOrder acasaOrder = new AcasaOrder();
		List<OrderItem> acasaOrderItemList = acasaOrder.getOrderItem();
		 //Loop cartItemList and create orderItemList and save in db
		for(CartItem cartItem: cartItemList) {
			OrderItem orderItem = orderItemService.addOrderItem(cartItem.getProduct(), cartItem.getQuantity());
		//add returned orderItem in AcasaOrder.orderItem	
			if(orderItem == null) throw new ServerException("order item is not added");
			acasaOrderItemList.add(orderItem);
		}
		acasaOrder.setIsCheckout(true);
		acasaOrder.setIsPaid(false);
		acasaOrder.setIsCompleted(false);
		acasaOrder.setIsCancelled(false);
		acasaOrder.setIsReturn(false);
		acasaOrder.setIsFailedDelivery(false);
		acasaOrder.setCreatedAt(LocalDate.now());
		acasaOrder.setDateOfCheckout(LocalDate.now());
		
		//Remove item in the cart if order is successful
		Cart cartInDb = cartService.getCartById(cartId);
		Outerloop:
		for(OrderItem order: acasaOrderItemList) {
			InnerLoop:
			for(CartItem item : cartItemList) {
				if(order.getProducts().getProductId().equals(item.getProduct().getProductId()) && order.getQuantity()== item.getQuantity()) {
					cartItemList.remove(item);
					break Outerloop;
				}else if(order.getProducts().getProductId().equals(item.getProduct().getProductId()) && order.getQuantity()< item.getQuantity()) {
					item.setQuantity(item.getQuantity() - order.getQuantity());
					break Outerloop;
				}
				
			}
		}
		Cart editedCart = cartService.editCart(cartId, cartItemList);
		if(editedCart == null ) throw new ServerException("Order unsuccessful");
		//save AcasaOrder in db
		return 	orderRepo.save(acasaOrder);
	}
}
