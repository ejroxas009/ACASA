package com.acasa.acasaApp.productinventory;

import java.time.LocalDate;

import com.acasa.acasaApp.products.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ProductInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productInventoryId;
	private double quantity;
	@OneToOne
	@JoinColumn(name = "productId")
	private Products product;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	public Long getProductInventoryId() {
		return productInventoryId;
	}
	public void setProductInventoryId(Long productInventoryId) {
		this.productInventoryId = productInventoryId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	
	
}
