package com.acasa.acasaApp.products;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.productinventory.ProductInventory;
import com.acasa.acasaApp.productinventory.ProductInventoryService;

@Service
public class ProductsService {

	private final ProductsRepo productsRepo;
	private final ProductInventoryService productInventoryService;

	public ProductsService(ProductsRepo productsRepo, ProductInventoryService productInventoryService) {
		super();
		this.productsRepo = productsRepo;
		this.productInventoryService = productInventoryService;
	}
	
	
	public List<Products> getAllProducts(){
		return productsRepo.findAll();
	}
	
	public Products getProductsById(Long id) throws NotFoundException {
		return productsRepo.findById(id).orElseThrow(NotFoundException:: new);
	}
	
	public Products addProducts(Products products, int quantity) throws ServerException {
		products.setCreatedAt(LocalDate.now());
		//add product inventory using productInventoryService
		ProductInventory newProductInventory = productInventoryService.createProductInventory(products, quantity);
		if(newProductInventory == null) throw new ServerException("Product is not added successfully");
		return productsRepo.save(products);
	}
	
	public Products editProduct(Long id, Products product) throws NotFoundException {
		Products productInDb = productsRepo.findById(id).orElseThrow(NotFoundException::new);
		productInDb.setProductName(product.getProductName());
		productInDb.setProductCategory(product.getProductCategory());
		productInDb.setProductGroup(product.getProductGroup());
		productInDb.setProductDescription(product.getProductDescription());
		productInDb.setProductPrice(product.getProductPrice());
		productInDb.setProductVariation(product.getProductVariation());
		productInDb.setProductVariationDescription(product.getProductVariationDescription());
		productInDb.setModifiedAt(LocalDate.now());
		return productsRepo.save(productInDb);
	}
	
	public Products addVideo(Long id, String videoUrl) throws NotFoundException {
		Products productInDb = productsRepo.findById(id).orElseThrow(NotFoundException:: new);
		productInDb.setProductVideo(videoUrl);
		return productsRepo.save(productInDb);
	}
	
	public Products setAvailable(Long id) throws NotFoundException {
		Products productInDb = productsRepo.findById(id).orElseThrow(NotFoundException::new);
		productInDb.setIsAvailable(!productInDb.getIsAvailable());
		return productsRepo.save(productInDb);
	}
	
	public Products setDiscount(Long id, double discountPercentage) throws NotFoundException {
		Products productInDb = productsRepo.findById(id).orElseThrow(NotFoundException::new);
		productInDb.setDiscount(discountPercentage);
		productInDb.setProductPrice(productInDb.getProductPrice()*(1- productInDb.getDiscount()));
		return productsRepo.save(productInDb);
	}
}
