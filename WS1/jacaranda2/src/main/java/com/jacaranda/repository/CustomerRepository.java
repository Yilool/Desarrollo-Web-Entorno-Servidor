package com.jacaranda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Customer;

@Repository(value = "customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
