package com.acasa.acasaApp.productimage;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

	private final ProductImageRepo productImageRepo;

	public ProductImageService(ProductImageRepo productImageRepo) {
		super();
		this.productImageRepo = productImageRepo;
	}
	
	public List<ProductImage> getAllProductImage() {
		return  productImageRepo.findAll();
	}
	
	public ProductImage getProductImageById(Long id) throws NotFoundException {
		return productImageRepo.findById(id).orElseThrow(NotFoundException::new);
		
	}
	
	public List<ProductImage> getProductImageByProductId(Long productId) {
		return productImageRepo.findAll()
				.stream()
				.filter(image -> image.getProduct().getProductId().equals(productId))
				.collect(Collectors.toList());
	}
	
	public ProductImage addImage(ProductImage image) {
		image.setCreatedAt(LocalDate.now());
		return productImageRepo.save(image);
	}
	
	public ProductImage changeProductImage(Long productImageId, String productImageUrl) throws NotFoundException {
		ProductImage productImageInDb = productImageRepo.findById(productImageId).orElseThrow(NotFoundException::new);
		productImageInDb.setUrl(productImageUrl);
		productImageInDb.setModifiedAt(LocalDate.now());
		return productImageRepo.save(productImageInDb);
	}
}
