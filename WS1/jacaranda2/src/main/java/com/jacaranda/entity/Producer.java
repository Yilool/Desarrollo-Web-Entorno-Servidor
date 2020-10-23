package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Producer implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;
	
	public Producer() {
		this.products = new ArrayList<>();
	}
	
	public Producer(String name) {
		this.setName(name);
		this.products = new ArrayList<>();
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void rmProduct(Product p) {
		products.remove(p);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
