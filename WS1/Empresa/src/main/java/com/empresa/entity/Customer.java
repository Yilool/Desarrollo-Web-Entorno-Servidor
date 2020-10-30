package com.empresa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Comparable<Customer>, Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cusId;
	private String cusName;
	private String cusSurname;
	@OneToMany
	@JoinColumn(name="product_id", foreignKey = @ForeignKey(name="product_id_fk"), nullable = false)
	private List<Product> cusProducts;
	
	public Customer() {
		this.cusProducts = new ArrayList<>();
	}
	
	public Customer(String name, String surname) {
		this.cusName = name;
		this.cusSurname = surname;
		this.cusProducts = new ArrayList<>();
	}
	
	public void addCusProduct(Product p) {
		cusProducts.add(p);
	}
	
	public void rmCusProduct(Product p) {
		cusProducts.remove(p);
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusSurname() {
		return cusSurname;
	}

	public void setCusSurname(String cusSurname) {
		this.cusSurname = cusSurname;
	}

	public Integer getCusId() {
		return cusId;
	}

	public ArrayList<Product> getCusProducts() {
		ArrayList<Product> aux = new ArrayList<>();
		
		aux.addAll(cusProducts);
		
		return aux;
	}

	@Override
	public int compareTo(Customer other) {
		return Integer.valueOf(this.cusId).compareTo(other.getCusId());
	}
}
