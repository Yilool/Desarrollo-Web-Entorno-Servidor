package com.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
