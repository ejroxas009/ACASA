package com.acasa.acasaApp.products;

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
@RequestMapping(path = "/api/v1/products")
public class ProductsController {

	private final ProductsService productsService;

	@Autowired
	public ProductsController(ProductsService productsService) {
		super();
		this.productsService = productsService;
	}
	
	@GetMapping
	public ResponseEntity<List<Products>> getAllProducts() throws ServerException{
		List<Products> products = productsService.getAllProducts();
		if(products == null) throw new ServerException(null);
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Products> getProductsById(@PathVariable Long id) throws ServerException, NotFoundException{
		Products product = productsService.getProductsById(id);
		if(product == null) throw new ServerException(null);
		return ResponseEntity.ok().body(product);
	}
	
	@PostMapping
	public ResponseEntity addProducts(@RequestBody Products products) throws ServerException {
		Products product = productsService.addProducts(products);
		if(product == null) throw new ServerException(null);
		return ResponseEntity.ok("Successfully added a product");
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity editProducts(@PathVariable Long id, @RequestBody Products product) throws NotFoundException, ServerException {
		Products editedProduct = productsService.editProduct(id, product);
		if(editedProduct == null) throw new ServerException(null);
		return ResponseEntity.ok("Successfully Edited a product");
		
	}
	
	@PutMapping(path = "/id")
	public ResponseEntity addVideo(@PathVariable Long id, @RequestBody String videoUrl) throws NotFoundException, ServerException {
		Products product = productsService.addVideo(id, videoUrl);
		if(!product.getProductVideo().equals(videoUrl)) throw new ServerException(null);
		return ResponseEntity.ok("Successfully added a video");
	}
	
	@PutMapping(path = "/set-available/{id}")
	public ResponseEntity setAvailable(@PathVariable Long id) throws NotFoundException {
		Products product = productsService.setAvailable(id);
		return ResponseEntity.ok("Successfully toggled isAvailable");
	}
}
