package com.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
