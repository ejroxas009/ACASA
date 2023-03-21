package com.acasa.acasaApp.productimage;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/product-image")
public class ProductImageController {

	private final ProductImageService productImageService;

	@Autowired
	public ProductImageController(ProductImageService productImageService) {
		super();
		this.productImageService = productImageService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductImage>> getAllProductImage() throws ServerException{
		List<ProductImage> productImage = productImageService.getAllProductImage();
		if(productImage == null) throw new ServerException(null);
		return ResponseEntity.ok().body(productImage);
	} 
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductImage> getProductImageById(@PathVariable Long id) throws ServerException, NotFoundException{
		ProductImage productImage = productImageService.getProductImageById(id);
		if(productImage == null) throw new ServerException(null);
		return ResponseEntity.ok().body(productImage);
	}
	
	@GetMapping(path = "/{productId}")
	public ResponseEntity<List<ProductImage>> getProductImageByProductId(@PathVariable Long productId) throws ServerException{
		List<ProductImage> productImage = productImageService.getProductImageByProductId(productId);
		if(productImage == null) throw new ServerException(null);
		return ResponseEntity.ok().body(productImage);
	}
	
	@PostMapping
	public ResponseEntity addProductImage(@RequestBody ProductImage productImage) throws ServerException {
		ProductImage returnedProductImage = productImageService.addImage(productImage);
		if(returnedProductImage == null) throw new ServerException(null);
		return ResponseEntity.ok("Successfully added a product image");
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity changeProductImage(@PathVariable Long id, @RequestBody String url) throws NotFoundException, ServerException {
		ProductImage returnedProductImage = productImageService.changeProductImage(id, url);
		if(returnedProductImage == null) throw new ServerException(null);
		return ResponseEntity.ok("successfully changed an image");
	}
}
