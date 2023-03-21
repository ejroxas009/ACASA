package com.acasa.acasaApp.order;

import java.time.LocalDate;
import java.util.List;

import com.acasa.acasaApp.appuser.AppUser;
import com.acasa.acasaApp.orderitem.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class AcasaOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_appuserid")
	private AppUser appUser;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_orderId")
	private List<OrderItem> orderItem;
	private String orderNumber;
	private Boolean isCheckout;
	private Boolean isPaid;
	private Boolean isCompleted;
	private Boolean isCancelled;
	private Boolean isReturn;
	private Boolean isFailedDelivery;
	private LocalDate dateOfCheckout;
	private LocalDate dateOfPayment;
	private LocalDate dateCompleted;
	private LocalDate dateOfReturn;
	private LocalDate dateOfFailedDelivery;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Boolean getIsCheckout() {
		return isCheckout;
	}
	public void setIsCheckout(Boolean isCheckout) {
		this.isCheckout = isCheckout;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public Boolean getIsCancelled() {
		return isCancelled;
	}
	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public Boolean getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}
	public Boolean getIsFailedDelivery() {
		return isFailedDelivery;
	}
	public void setIsFailedDelivery(Boolean isFailedDelivery) {
		this.isFailedDelivery = isFailedDelivery;
	}
	public LocalDate getDateOfCheckout() {
		return dateOfCheckout;
	}
	public void setDateOfCheckout(LocalDate dateOfCheckout) {
		this.dateOfCheckout = dateOfCheckout;
	}
	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public LocalDate getDateCompleted() {
		return dateCompleted;
	}
	public void setDateCompleted(LocalDate dateCompleted) {
		this.dateCompleted = dateCompleted;
	}
	public LocalDate getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public LocalDate getDateOfFailedDelivery() {
		return dateOfFailedDelivery;
	}
	public void setDateOfFailedDelivery(LocalDate dateOfFailedDelivery) {
		this.dateOfFailedDelivery = dateOfFailedDelivery;
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
