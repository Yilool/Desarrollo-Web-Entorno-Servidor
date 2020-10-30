package com.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.empresa.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    public Customer findCustomerById(Integer id);

    public default List<Customer> getAllOrderById() {
        List<Customer> resultList = new ArrayList<>();

        findAll().forEadh(resultList::add);

        return resultList.stream().sorted().collect(Collectors.toList());
    }
}
