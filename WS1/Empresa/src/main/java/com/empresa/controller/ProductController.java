package com.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Product;
import com.empresa.repository.ProductRepository;

@RestController
@RequestMapping(path = "/empresa")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping(path = "/product")
	public ResponseEntity<?> postProduct(@RequestBody Product product) {
		ResponseEntity<?> res = null;
		
		productRepository.save(product);	
		res = ResponseEntity.status(HttpStatus.OK).body(product);
		
		return res;
	}
	
	@GetMapping(path = "/product")
	public ResponseEntity<?> getAllProduct() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
	}
	
	@GetMapping(path = "/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		ResponseEntity<?> res = null;
		
		if (productRepository.existsById(id)) {
			ResponseEntity.status(HttpStatus.OK).body(productRepository.findById(id));
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
		}
		return res;
	}
	
	@PutMapping(path = "/product")
	public ResponseEntity<?> putProduct(@RequestBody Product product) {
		ResponseEntity<?> res = null;
		
		if (productRepository.existsById(product.getPrdId())) {
			Product p = productRepository.findProductById(product.getPrdId());
			p.setPrdName(product.getPrdName());
			p.setPrdPrice(product.getPrdPrice());
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+product.getPrdId());
		}
		return res;
	}
	
	@DeleteMapping(path = "/product/{id}")
	public ResponseEntity<?> delProduct(@PathVariable Integer id) {
		ResponseEntity<?> res = null;
		
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			ResponseEntity.status(HttpStatus.OK).body("Producto con id: "+id+" borrado");
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
		}
		
		return res;
	}
}
