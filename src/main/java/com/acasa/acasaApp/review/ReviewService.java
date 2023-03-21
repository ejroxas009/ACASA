package com.acasa.acasaApp.review;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	private final ReviewRepo reviewRepo;

	public ReviewService(ReviewRepo reviewRepo) {
		super();
		this.reviewRepo = reviewRepo;
	}
	
	
	public List<Review> getAllReview(){
		return reviewRepo.findAll();
	}
	
	public Review getReviewById(Long reviewId) throws NotFoundException {
		return reviewRepo.findById(reviewId).orElseThrow(NotFoundException::new);
	}
	
	
	public List<Review> getReviewByProductId(Long productId) {
		return reviewRepo.findAll()
				.stream()
				.filter(review-> review.getProducts().getProductId().equals(productId))
				.collect(Collectors.toList());
	}
	
	public List<Review> getReviewByUserId(Long userId){
		return reviewRepo.findAll()
				.stream()
				.filter(review->review.getAppUser().getUserId().equals(userId))
				.collect(Collectors.toList());
	}
	
	public Review createReview(Review review) {
		return reviewRepo.save(review);
	}
	
	
	
	
}
