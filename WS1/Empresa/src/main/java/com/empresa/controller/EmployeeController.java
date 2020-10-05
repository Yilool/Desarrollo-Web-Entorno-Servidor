package com.empresa.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Employee;
import com.empresa.entity.Product;
import com.empresa.service.AppService;

@RestController
@RequestMapping(path = "/empresa")
public class EmployeeController {
	@Autowired
	private AppService s; 
	
	@PostMapping(path = "/post-employee")
	public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
		ResponseEntity<?> res = null;
		
		s.getEmployees().add(employee);
		res = ResponseEntity.status(HttpStatus.OK).body(employee);
		
		return res;
	}
	
	@GetMapping(path = "/get-all-employee")
	public ResponseEntity<?> getAllEmployee() {
		return ResponseEntity.status(HttpStatus.OK).body(s.getEmployees().stream().sorted().collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/get-employee")
	public ResponseEntity<?> getEmployee(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Employee e1 = s.getEmployees().stream().filter(e -> e.getEmpId() == id).findFirst().orElse(null);
		
		if (s.getEmployees() == null || s.getEmployees().isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay empleados en la base");
		} else if (e1 == null && !s.getEmployees().contains(e1)) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleados");
		} else if (s.getEmployees().contains(e1)) {
			res = ResponseEntity.status(HttpStatus.OK).body(e1);
		}
		return res;
	}
	
	@PutMapping(path = "/put-employee")
	public ResponseEntity<?> putEmployee(@RequestBody Employee employee) {
		ResponseEntity<?> res = null;
		Employee e1 = s.getEmployees().stream().filter(e -> e.getEmpId() == employee.getEmpId()).findFirst().orElse(null);
		
		if (e1 != null) {
			e1.setEmpName(employee.getEmpName());
			e1.setEmpSurname(employee.getEmpSurname());
			res = ResponseEntity.status(HttpStatus.OK).body(e1);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleados");
		}
		
		return res;
	}
	
	@DeleteMapping(path = "/delete-employee")
	public ResponseEntity<?> delProduct(@RequestParam int id) {
		ResponseEntity<?> res = null;
		Employee e1 = s.getEmployees().stream().filter(e -> e.getEmpId() == id).findFirst().orElse(null);
		
		if (e1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado");
		} else {
			s.getEmployees().remove(e1);
			res = ResponseEntity.status(HttpStatus.OK).body(e1);
		}
		
		return res;
	}
	
	//Añadir producto al empleado
		@PutMapping(path = "put-employee-product")
		public ResponseEntity<?> putEmpPrd(@RequestParam int prdId, @RequestParam int empId) {
			ResponseEntity<?> res = null;
			Product p1 = s.getProducts().stream().filter(p -> p.getPrdId() == prdId).findFirst().orElse(null);
			Employee e1 = s.getEmployees().stream().filter(e -> e.getEmpId() == empId).findFirst().orElse(null);
				
			if (s.getProducts() == null || s.getProducts().isEmpty()) {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen productos");
			} else if (s.getEmployees() == null || s.getEmployees().isEmpty()) {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen empleados");
			} else if (p1 == null) {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
			} else if (e1 == null) {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado");
			} else {
				e1.addEmpProduct(p1);
				res = ResponseEntity.status(HttpStatus.OK).body(e1);
			}
				
			return res;
		
		}
	
}
