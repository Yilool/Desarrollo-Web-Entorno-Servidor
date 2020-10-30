package com.empresa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee implements Comparable<Employee>, Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String surname;
	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "productos_empleado", foreignKey = @ForeignKey(name="product_id_employee_fk"), nullable = false)
 	private List<Product> products;
	
	public Employee() {
		this.products = new ArrayList<>();
	}
	
	public Employee(String name, String surname) {
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

	public void setName(String empName) {
		this.name = empName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String empSurname) {
		this.surname = empSurname;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> aux = new ArrayList<>();
		
		aux.addAll(products);
		
		return aux;
	}

	@Override
	public int compareTo(Employee other) {
		return Integer.valueOf(this.id).compareTo(other.getId());
	}
}
