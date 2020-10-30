package com.empresa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	private Integer id;
	private String name;
	private String surname;
	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "productos_cliente", foreignKey = @ForeignKey(name="product_id_custom_fk"), nullable = false)
	private List<Product> products;
	
	public Customer() {
		this.products = new ArrayList<>();
	}
	
	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
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

	public void setName(String cusName) {
		this.name = cusName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProducts(List<Product> cusProducts) {
		this.products = cusProducts;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String cusSurname) {
		this.surname = cusSurname;
	}



	public ArrayList<Product> getProducts() {
		ArrayList<Product> aux = new ArrayList<>();
		
		aux.addAll(products);
		
		return aux;
	}

	@Override
	public int compareTo(Customer other) {
		return Integer.valueOf(this.id).compareTo(other.getId());
	}
}
