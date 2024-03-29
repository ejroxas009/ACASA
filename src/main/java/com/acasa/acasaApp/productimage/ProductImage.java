package com.acasa.acasaApp.productimage;

import java.time.LocalDate;

import com.acasa.acasaApp.products.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productImageId;
	private String url;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Products product;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	
	
	
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public Long getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
