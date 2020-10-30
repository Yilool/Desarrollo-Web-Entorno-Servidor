package com.empresa.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Employee;
import com.empresa.entity.Product;
import com.empresa.service.EmployeeService;

@RestController
@RequestMapping(path = "/empresa")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(path = "/employee")
	public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
		return employeeService.crearEmpleado(employee);
	}
	
	@GetMapping(path = "/employee")
	public ResponseEntity<?> getAllEmployee() {
		return employeeService.obtenerEmpleados();
	}
	
	@GetMapping(path = "/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer id) {
		return employeeService.obtenerEmpleado(id);
	}
	
	@PutMapping(path = "/employee")
	public ResponseEntity<?> putEmployee(@RequestBody Employee employee) {
		return employeeService.actualizarEmpleado(employee);
	}
	
	@DeleteMapping(path = "/employee/{id}")
	public ResponseEntity<?> delProduct(@PathVariable Integer id) {
		return employeeService.borrarEmpleado(id);
	}
	
	//Añadir producto al empleado
	@PutMapping(path = "/employee/{empId}&&{prdId}")
	public ResponseEntity<?> putEmpPrd(@PathVariable int empId, @PathVariable int prdId) {
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
	
	//Borrar producto al empleado
	@DeleteMapping(path = "/employee/{empId}&&{prdId}")
	public ResponseEntity<?> delEmpPrd(@PathVariable int empId, @PathVariable int prdId) {
		ResponseEntity<?> res = null;
		Product p1 = s.getProducts().stream().filter(p -> p.getPrdId() == prdId).findFirst().orElse(null);
		Employee e1 = s.getEmployees().stream().filter(e -> e.getEmpId() == empId).findFirst().orElse(null);
		Product ep1 = e1.getEmpProducts().stream().filter(ep -> ep.getPrdId() == prdId).findFirst().orElse(null);
		
		if (s.getProducts() == null || s.getProducts().isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen productos");
		} else if (s.getEmployees() == null || s.getEmployees().isEmpty()) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen empleados");
		} else if (p1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
		} else if (e1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado");
		} else if (ep1 == null) {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El empleado no desarrolló dicho producto");
		} else {
			e1.rmEmpProduct(ep1);
			res = ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(e1, ep1));
		}
		
		return res;
	}
	
}
