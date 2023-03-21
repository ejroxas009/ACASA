package com.acasa.acasaApp.cartitem;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.products.Products;
import com.acasa.acasaApp.products.ProductsService;

@Service
public class CartItemService {

	private final CartItemRepo cartItemRepo;
	private final ProductsService productsService;

	
	@Autowired
	public CartItemService(CartItemRepo cartItemRepo, ProductsService productsService) {
		super();
		this.cartItemRepo = cartItemRepo;
		this.productsService = productsService;
	}


	public List<CartItem> getAllCartItem(){
		return cartItemRepo.findAll();
	}
	
	public CartItem getCartItemById(Long cartItemId) throws NotFoundException {
		return cartItemRepo.findById(cartItemId).orElseThrow(NotFoundException :: new);
	}
	
	
	public CartItem addCartItem(Long productId) throws NotFoundException {
		CartItem cartItem = new CartItem();
		Products product = productsService.getProductsById(productId);
		cartItem.setProduct(product);
		cartItem.setCreatedAt(LocalDate.now());
		return cartItemRepo.save(cartItem);
	}
	
	public CartItem editQuantity(Long cartItemId, int quantity) throws NotFoundException {
		CartItem cartItemInDb = this.getCartItemById(cartItemId);
		cartItemInDb.setQuantity(quantity);
		return cartItemRepo.save(cartItemInDb);
	}
	
	
}
