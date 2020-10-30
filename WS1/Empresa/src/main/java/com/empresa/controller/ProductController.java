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
import com.empresa.service.ProductService;

@RestController
@RequestMapping(path = "/empresa")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping(path = "/product")
	public ResponseEntity<?> postProduct(@RequestBody Product product) {
		return productService.crearProducto(product);
	}
	
	@GetMapping(path = "/product")
	public ResponseEntity<?> getAllProduct() {
		return productService.obtenerProductos();
	}
	
	@GetMapping(path = "/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {p
		return productService.obtenerProducto(id);
	}
	
	@PutMapping(path = "/product")
	public ResponseEntity<?> putProduct(@RequestBody Product product) {
		return productService.actualizarProducto(p);
	}
	
	@DeleteMapping(path = "/product/{id}")
	public ResponseEntity<?> delProduct(@PathVariable Integer id) {
		return productService.borrarProducto(id);
	}
}
