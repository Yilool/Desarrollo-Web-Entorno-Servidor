package com.empresa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements Comparable<Product>, Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private double price;
	
	public Product() {
	}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getPrdName() {
		return name;
	}

	public void setPrdName(String prdName) {
		this.name = prdName;
	}

	public double getPrdPrice() {
		return price;
	}

	public void setPrdPrice(double prdPrice) {
		this.price = prdPrice;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(Product other) {

		return Integer.valueOf(this.getId()).compareTo(other.getId());
	}
}
