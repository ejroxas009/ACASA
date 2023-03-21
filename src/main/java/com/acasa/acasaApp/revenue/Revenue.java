package com.acasa.acasaApp.revenue;

import java.time.LocalDate;

import com.acasa.acasaApp.products.Products;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Revenue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long revenueId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_productId")
	private Products products;
	private double unitPrice;
	private double quantity;
	private double totalPrice;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	public Long getRevenueId() {
		return revenueId;
	}
	public void setRevenueId(Long revenueId) {
		this.revenueId = revenueId;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
