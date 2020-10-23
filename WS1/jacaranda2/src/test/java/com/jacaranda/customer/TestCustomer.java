package com.jacaranda.customer;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.SuscriptionEnum;
import com.jacaranda.entity.old.ComparaClienteDNI;
import com.jacaranda.entity.old.ComparaClienteNombre;

class TestCustomer {

	private List<Customer> customers = new ArrayList<>() {
		{
			add(new Customer("Alvaro", "Sánchez", "Sevilla", "11111111A", SuscriptionEnum.BASIC));
			add(new Customer("Yi", "Chen", "Sevilla", "22222222B", SuscriptionEnum.BASIC));
			add(new Customer("Cliente", "Largo", LocalDate.of(1992, 10, 22), "address", "city", "dni", "country", "666444999", "Masculino", SuscriptionEnum.BASIC));
			add(new Customer("Raul", "Morales", "Sevilla", "33333333C", SuscriptionEnum.PREMIUM));
		}
	};
	

	
	@Test
	void testFailOnNull() {
		List<Customer> customers = null;
		try {
			customers.stream().sorted();
		} catch (Exception e) {
			assert(false);
		}
	}
	
	
	@Test
	void testFailOnEmpty() {
		List<Customer> cust = new ArrayList<>();
		try {
			cust.stream().sorted();
		} catch (Exception e) {
			assert(false);
		}
		
	}


	
	public List<Customer> getCustomers() {
		return customers;
	}

	@Test
	void testOrderedCollection() {
//		customers.stream().sorted();
		Collections.sort(customers, new ComparaClienteDNI());
		
		System.out.println(getCustomers());
	}

	
	
}
