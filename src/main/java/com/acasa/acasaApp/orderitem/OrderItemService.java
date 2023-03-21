package com.acasa.acasaApp.orderitem;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.products.Products;

@Service
public class OrderItemService {

	private final OrderItemRepo orderItemRepo;

	public OrderItemService(OrderItemRepo orderItemRepo) {
		super();
		this.orderItemRepo = orderItemRepo;
	}
	
	public List<OrderItem> getAllOrderItem() {
		return orderItemRepo.findAll();
	}
	
	public OrderItem getOrderItemById(Long orderItemId) throws NotFoundException {
		return orderItemRepo.findById(orderItemId).orElseThrow(NotFoundException:: new);
	}
	
	
	public OrderItem addOrderItem(Products product, int quantity) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProducts(product);
		orderItem.setQuantity(quantity);
		orderItem.setCreatedAt(LocalDate.now());
		
		return orderItemRepo.save(orderItem);
	}
	
	
	public OrderItem editQuantity(Long orderItemId, int quantity) throws NotFoundException {
		OrderItem orderItemInDb = this.getOrderItemById(orderItemId);
		orderItemInDb.setQuantity(quantity);
		orderItemInDb.setCreatedAt(LocalDate.now());
		return orderItemRepo.save(orderItemInDb);
	}
}
