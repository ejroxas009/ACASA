package com.acasa.acasaApp.cart;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.appuser.AppUser;
import com.acasa.acasaApp.appuser.AppUserService;
import com.acasa.acasaApp.cartitem.CartItem;
import com.acasa.acasaApp.cartitem.CartItemService;
import com.acasa.acasaApp.products.Products;

@Service
public class CartService {

	private final CartRepo cartRepo;
	private final AppUserService appUserService;
	private final CartItemService cartItemService;

	@Autowired
	

	public CartService(CartRepo cartRepo, AppUserService appUserService, CartItemService cartItemService) {
		super();
		this.cartRepo = cartRepo;
		this.appUserService = appUserService;
		this.cartItemService = cartItemService;
	}
	
	public List<Cart> getAllCart(){
		return cartRepo.findAll();
	}
	
	public Cart getCartById(Long cartId) throws NotFoundException {
		return cartRepo.findById(cartId).orElseThrow(NotFoundException::new);
	}
	
	public Cart addCart(Long userId) throws NotFoundException {
		Cart cart = new Cart();
		AppUser user = appUserService.getUserById(userId);
		cart.setAppUser(user);
		cart.setCreatedAt(LocalDate.now());
		return cartRepo.save(cart);
	}


	public Cart addCartItem(Long userId,Long productId) throws NotFoundException, ServerException {
		CartItem cartItem = cartItemService.addCartItem(productId);
		if(cartItem == null) throw new ServerException("Failed to add in cart");
		Cart cartInDb = (Cart) cartRepo.findAll()
				.stream()
				.filter(cart -> cart.getAppUser().getUserId().equals(userId))
				.limit(1)
				.collect(Collectors.toList());
		List<CartItem> cartItemList = cartInDb.getCartItem();
		cartItemList.add(cartItem);
		cartInDb.setCartItem(cartItemList);
		return cartRepo.save(cartInDb);
	}
	
	public Cart removeCartItem(Long userId, List<Products> products) {
		Cart cartInDb = (Cart) cartRepo.findAll()
				.stream()
				.filter(cart -> cart.getAppUser().getUserId().equals(userId))
				.limit(1)
				.collect(Collectors.toList());

		List<CartItem> cartItemList = cartInDb.getCartItem();
		outerloop:		
		for(CartItem item : cartItemList) {
					for(Products product : products) {
						if(item.getProduct().getProductId().equals(product.getProductId())) {
							cartItemList.remove(item);
							break outerloop;
						}
					}
				}
		return cartRepo.save(cartInDb);
	}
	
//Need to finish cart item service
	public List<CartItem> getAllCartItemInCartByUserId(Long userId) {
		Cart cartOfUser =  (Cart) cartRepo.findAll()
				.stream()
				.filter(cart->cart.getAppUser().getUserId().equals(userId))
				.limit(1)
				.collect(Collectors.toList());
		
		List<CartItem> cartItems = cartOfUser.getCartItem();
		return cartItems;
	}
	
	public Cart editCart(Long cartId, List<CartItem> newCartItemList) throws NotFoundException {
		Cart cartInDb = this.getCartById(cartId);
		cartInDb.setCartItem(newCartItemList);
		cartInDb.setModifiedAt(LocalDate.now());
		return cartRepo.save(cartInDb);
	}
	
	
	
	
}
