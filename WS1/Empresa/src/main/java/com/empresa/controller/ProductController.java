package com.empresa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Product;

@RestController
@RequestMapping(path = "/empresa")
public class ProductController {
	private List<Product> products = new ArrayList<>();
	
	@PostMapping(path = "/post-product")
	public ResponseEntity<?> postProduct(@RequestBody Product product) {
		ResponseEntity<?> res = null;
		
		products.add(product);
		
		return res;
	}
	
	@GetMapping(path = "/get-all-product")
	public ResponseEntity<?> getAllProduct() {
		return ResponseEntity.status(HttpStatus.OK).body(products.stream().sorted().collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/get-product")
	public ResponseEntity<?> getProduct(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Product p1 = products.stream().filter(p -> p.getPrdId() == id).findFirst().orElse(null);
		
		if (products == null || products.isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos en la base");
		} else if (p1 == null && !products.contains(p1)) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
		} else if (products.contains(p1)) {
			res = ResponseEntity.status(HttpStatus.OK).body(p1);
		}
		return res;
	}
	
	@PutMapping(path = "/put-product")
	public ResponseEntity<?> putProduct(@RequestBody Product product) {
		ResponseEntity<?> res = null;
		Product p1 = products.stream().filter(p -> p.getPrdId() == product.getPrdId()).findFirst().orElse(null);
		
		if (p1 != null) {
			p1.setPrdName(product.getPrdName());
			p1.setPrdPrice(product.getPrdPrice());
			res = ResponseEntity.status(HttpStatus.OK).body(p1);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
		}
		
		return res;
	}
	
	@DeleteMapping(path = "/delete-product")
	public ResponseEntity<?> delProduct(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Product p1 = products.stream().filter(p -> p.getPrdId() == id).findFirst().orElse(null);
		
		if (p1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
		} else {
			products.remove(p1);
			res = ResponseEntity.status(HttpStatus.OK).body(p1);
		}
		
		return res;
	}
}
