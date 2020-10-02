package com.empresa.service;

import java.util.ArrayList;

import java.util.List;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empresa.entity.Customer;
import com.empresa.entity.Employee;
import com.empresa.entity.Product;

@Service
public class AppService {
	private List<Customer> customers = new ArrayList<>();
	private List<Product> products = new ArrayList<>();
	private List<Employee> employees = new ArrayList<>();
	
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addPrd(char c) throws Exception {
		char caracter = Character.toUpperCase(c);
		
		if (caracter == 'E') {
			
		} else if (caracter == 'C') {
			
		} else {
			throw new Exception("No se puede añadir el producto");
		}
	}
	
	//Añadir producto al empleado
	@PutMapping(path = "put-employee-product")
	public ResponseEntity<?> putEmpPrd(@RequestParam int prdId, @RequestParam int empId) {
		ResponseEntity<?> res = null;
		Product p1 = products.stream().filter(p -> p.getPrdId() == prdId).findFirst().orElse(null);
		Employee e1 = employees.stream().filter(e -> e.getEmpId() == empId).findFirst().orElse(null);
			
		if (products == null || products.isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen productos");
		} else if (employees == null || employees.isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen empleados");
		} else if (p1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
		} else if (e1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado");
		} else {
			e1.addEmpProduct(p1);
			res = ResponseEntity.status(HttpStatus.OK).body(e1);
		}
			
		return res;
	
	}
}
