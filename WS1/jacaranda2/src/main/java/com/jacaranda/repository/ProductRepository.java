package com.jacaranda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Product;

@Repository(value = "productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {

}
