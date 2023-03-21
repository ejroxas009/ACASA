package com.acasa.acasaApp.revenue;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.order.AcasaOrder;
import com.acasa.acasaApp.orderitem.OrderItem;

@Service
public class RevenueService {

	private final RevenueRepo revRepo;

	public RevenueService(RevenueRepo revRepo) {
		super();
		this.revRepo = revRepo;
	}
	
	public List<Revenue> getAllRevenue(){
		return revRepo.findAll();
	}
	
	public Revenue getRevenueById(Long revenueId) throws NotFoundException {
		return revRepo.findById(revenueId).orElseThrow(NotFoundException::new);
	}
	
	
	public double getRevenueByProductId(Long productId) {
		double totalRevenue = 0;
		List<Revenue> revenueList = revRepo.findAll()
				.stream()
				.filter(revenue-> revenue.getProducts().getProductId().equals(productId))
				.collect(Collectors.toList());
		for(Revenue revenue: revenueList) {
			totalRevenue += revenue.getTotalPrice();
		}	
		
		return totalRevenue;
		
	}
	
	
	public List<Revenue> addRevenue(AcasaOrder order) throws ServerException {
		if(order.getIsCompleted().equals(false)) throw new ServerException("Order is not completed");
		List<OrderItem> orderItemList = order.getOrderItem();
		List<Revenue> revenueList = new ArrayList<>();
		for(OrderItem item: orderItemList) {
			Revenue revenue = new Revenue();
			revenue.setProducts(item.getProducts());
			revenue.setQuantity(item.getQuantity());
			revenue.setUnitPrice(item.getProducts().getProductPrice());
			revenue.setTotalPrice(item.getQuantity()*item.getProducts().getProductPrice());
			revenue.setCreatedAt(LocalDate.now());
			revenueList.add(revRepo.save(revenue));
		}
		return revenueList;
	}
}
