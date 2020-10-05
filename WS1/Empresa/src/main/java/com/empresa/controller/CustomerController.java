package com.empresa.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.empresa.service.AppService;

@RestController
@RequestMapping(path = "/empresa")
public class CustomerController {
	@Autowired
	private AppService s; 
	
	@PostMapping(path = "/post-custom")
	public ResponseEntity<?> postCustom(@RequestBody Customer custom) {
		ResponseEntity<?> res = null;
		
		s.getCustomers().add(custom);
		res = ResponseEntity.status(HttpStatus.OK).body(custom);
		
		return res;
	}
	
	@GetMapping(path = "/get-all-custom")
	public ResponseEntity<?> getAllCustom() {
		return ResponseEntity.status(HttpStatus.OK).body(s.getCustomers().stream().sorted().collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/get-custom")
	public ResponseEntity<?> getCustom(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Customer c1 = s.getCustomers().stream().filter(c -> c.getCusId() == id).findFirst().orElse(null);
		
		if (s.getCustomers() == null || s.getCustomers().isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay clientes en la base");
		} else if (c1 == null && !s.getCustomers().contains(c1)) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
		} else if (s.getCustomers().contains(c1)) {
			res = ResponseEntity.status(HttpStatus.OK).body(c1);
		}
		return res;
	}
	
	@PutMapping(path = "/put-custom")
	public ResponseEntity<?> putCustom(@RequestBody Customer custom) {
		ResponseEntity<?> res = null;
		Customer c1 = s.getCustomers().stream().filter(c -> c.getCusId() == custom.getCusId()).findFirst().orElse(null);
		
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
		Customer c1 = s.getCustomers().stream().filter(c -> c.getCusId() == id).findFirst().orElse(null);
		
		if (c1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
		} else {
			s.getCustomers().remove(c1);
			res = ResponseEntity.status(HttpStatus.OK).body(c1);
		}
		
		return res;
	}
}
