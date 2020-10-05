package com.empresa.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
