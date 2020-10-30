package com.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.empresa.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
    public Employee findEmployeeById(Integer id);

    public default List<Employee> getAllOrderById() {
        List<Employee> resultList = new ArrayList<>();

        findAll().forEach(resultList::add);

        return resultList.stream().sorted().collect(Collectors.toList());
    }
}
