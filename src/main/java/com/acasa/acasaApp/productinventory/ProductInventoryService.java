package com.acasa.acasaApp.productinventory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.products.Products;

@Service
public class ProductInventoryService {

	private final ProductInventoryRepo inventoryRepo;

	@Autowired
	public ProductInventoryService(ProductInventoryRepo inventoryRepo) {
		super();
		this.inventoryRepo = inventoryRepo;
	}
	
	
	public List<ProductInventory> getAllProductInventory(){
		return inventoryRepo.findAll();
	}
	
	public ProductInventory getProductInventoryById(Long productInventoryId) throws NotFoundException {
		return inventoryRepo.findById(productInventoryId).orElseThrow(NotFoundException:: new);
	}
	
	public ProductInventory getProductInventoryByProductId(Long productId) {
		ProductInventory productInventory = (ProductInventory) inventoryRepo.findAll()
											.stream()	
											.filter(inventory-> inventory.getProduct().getProductId().equals(productId))
											.collect(Collectors.toList());
		
		return productInventory;
	}
	
	public ProductInventory createProductInventory(Products product, double quantity) {
		ProductInventory productInventory = new ProductInventory();
		productInventory.setProduct(product);
		productInventory.setQuantity(quantity);
		productInventory.setCreatedAt(LocalDate.now());
		
		return inventoryRepo.save(productInventory);
	}
	
	public ProductInventory setProductInventoryQuantity(Long productInventoryId, double quantity) throws NotFoundException {
		ProductInventory productInventoryInDb = this.getProductInventoryById(productInventoryId);
		productInventoryInDb.setQuantity(quantity);
		productInventoryInDb.setModifiedAt(LocalDate.now());
		return inventoryRepo.save(productInventoryInDb);
	}
}
