package com.jacaranda.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity(name = "Customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
//	private static int idSiguiente = 0;
	private String name;
	private String surname;
	private LocalDate birthdate;
	private String address;
	private String city;
	private String dni;
	private String country;
	private String mobileNumber;
	private String gender;
	private SuscriptionEnum tipoSuscripcion;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	private ArrayList<Visual> visuals;
	private List<Visual> visuals;


	
	/**
	 * Customer entity
	 * @author raul
	 * @param name
	 * @param surname
	 * @param city
	 * @param dni
	 */
	public Customer() {
	}
	
	public Customer(String name, String surname, String city, String dni, SuscriptionEnum tipoSuscripcion) {
		super();
//		this.id = idSiguiente++;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.dni = dni;
		this.tipoSuscripcion = tipoSuscripcion;
		this.visuals = new ArrayList<Visual>();
	}
	
	
	public Customer(String name, String surname, LocalDate birthdate, String address, String city, String dni,
			String country, String mobileNumber, String gender, SuscriptionEnum tipoSuscripcion) {
		super();
//		this.id = idSiguiente++;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.address = address;
		this.city = city;
		this.dni = dni;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.tipoSuscripcion = tipoSuscripcion;
		this.visuals = new ArrayList<Visual>();
	}

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
//	public ArrayList<Visual> getVisuals() {
//		return visuals;
//	}
	public List<Visual> getVisuals() {
		return visuals;
	}
	public void setVisuals(ArrayList<Visual> visuals) {
		this.visuals = visuals;
	}
	public SuscriptionEnum getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(SuscriptionEnum tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}

	
	
	
	
	public void addVisual(Visual v) {
		visuals.add(v);
	}
	
	public int contadorVisuals() {
		int cont = 0;

		for (Visual v : visuals) {
			if (v.getProducto().getIdProduct() >= 0) {
				cont++;
			}
		}

		return cont;
	}

	
	
}
