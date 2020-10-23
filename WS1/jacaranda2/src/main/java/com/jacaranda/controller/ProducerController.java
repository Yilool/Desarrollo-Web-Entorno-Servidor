package com.jacaranda.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Producer;
import com.jacaranda.repository.ProducerRepository;

@RestController
@RequestMapping(path = "/netflix")
public class ProducerController {
	@Autowired
	private ProducerRepository producerRepository;

	@PostMapping("/producer")
	public Producer createCustomer(@RequestBody Producer p) {
		return producerRepository.save(p);
	}
	
	@GetMapping("/producer")
	public ResponseEntity<?> getAllProducer() {
		return ResponseEntity.status(HttpStatus.OK).body(producerRepository.findAll());
	}
	
	@GetMapping("/producer/{id}")
	public ResponseEntity<?> getProducer(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(producerRepository.findById(id));
	}
}
