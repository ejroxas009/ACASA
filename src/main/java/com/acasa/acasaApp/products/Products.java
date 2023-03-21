package com.acasa.acasaApp.products;

import java.time.LocalDate;
import java.util.List;


import com.acasa.acasaApp.productimage.ProductImage;
import com.acasa.acasaApp.productinventory.ProductInventory;
import com.acasa.acasaApp.review.Review;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private ProductCategory productCategory;
	private int productGroup;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductImage> productImage;
	private String productVideo;
	private String productDescription;
	private Double productPrice;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
	private ProductInventory productInventory;
	private double discount;
	private int productVariation;
	private String productVariationDescription;
	private Boolean isAvailable;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
	private List<Review> review;
	private Double averageRating;
	private int noOfReviews;
	private int noOfSales;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(int productGroup) {
		this.productGroup = productGroup;
	}
	public List<ProductImage> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}
	public String getProductVideo() {
		return productVideo;
	}
	public void setProductVideo(String productVideo) {
		this.productVideo = productVideo;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public ProductInventory getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(ProductInventory productInventory) {
		this.productInventory = productInventory;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getProductVariation() {
		return productVariation;
	}
	public void setProductVariation(int productVariation) {
		this.productVariation = productVariation;
	}
	public String getProductVariationDescription() {
		return productVariationDescription;
	}
	public void setProductVariationDescription(String productVariationDescription) {
		this.productVariationDescription = productVariationDescription;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> review) {
		this.review = review;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	public int getNoOfReviews() {
		return noOfReviews;
	}
	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}
	public int getNoOfSales() {
		return noOfSales;
	}
	public void setNoOfSales(int noOfSales) {
		this.noOfSales = noOfSales;
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
