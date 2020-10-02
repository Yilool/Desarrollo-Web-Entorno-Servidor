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

import com.empresa.entity.Customer;
import com.empresa.entity.Product;

@RestController
@RequestMapping(path = "/empresa")
public class CustomerController {
	private List<Customer> customers = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	
	@PostMapping(path = "/post-custom")
	public ResponseEntity<?> postCustom(@RequestBody Customer custom) {
		ResponseEntity<?> res = null;
		
		customers.add(custom);
		res = ResponseEntity.status(HttpStatus.OK).body(custom);
		
		return res;
	}
	
	@GetMapping(path = "/get-all-custom")
	public ResponseEntity<?> getAllCustom() {
		return ResponseEntity.status(HttpStatus.OK).body(customers.stream().sorted().collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/get-custom")
	public ResponseEntity<?> getCustom(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Customer c1 = customers.stream().filter(c -> c.getCusId() == id).findFirst().orElse(null);
		
		if (customers == null || customers.isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay clientes en la base");
		} else if (c1 == null && !customers.contains(c1)) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
		} else if (customers.contains(c1)) {
			res = ResponseEntity.status(HttpStatus.OK).body(c1);
		}
		return res;
	}
	
	@PutMapping(path = "/put-custom")
	public ResponseEntity<?> putCustom(@RequestBody Customer custom) {
		ResponseEntity<?> res = null;
		Customer c1 = customers.stream().filter(c -> c.getCusId() == custom.getCusId()).findFirst().orElse(null);
		
		if (c1 != null) {
			c1.setCusName(custom.getCusName());
			c1.setCusSurname(custom.getCusSurname());
			res = ResponseEntity.status(HttpStatus.OK).body(c1);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
		}
		
		return res;
	}
	
	@DeleteMapping(path = "/delete-custom")
	public ResponseEntity<?> delCustom(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Customer c1 = customers.stream().filter(c -> c.getCusId() == id).findFirst().orElse(null);
		
		if (c1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
		} else {
			customers.remove(c1);
			res = ResponseEntity.status(HttpStatus.OK).body(c1);
		}
		
		return res;
	}
}
