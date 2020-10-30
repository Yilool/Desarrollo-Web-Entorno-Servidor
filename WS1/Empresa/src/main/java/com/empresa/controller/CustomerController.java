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

import com.empresa.entity.Customer;
import com.empresa.entity.Product;
import com.empresa.service.CustomerService;
import com.empresa.service.ProductService;

@RestController
@RequestMapping(path = "/empresa")
public class CustomerController {
	@Autowired
	private CustomerService customerService; 
	
	@PostMapping(path = "/custom")
	public ResponseEntity<?> postCustom(@RequestBody Customer custom) {
		return customerService.crearCliente(custom);
	}
	
	@GetMapping(path = "/custom")
	public ResponseEntity<?> getAllCustom() {
		return customerService.obtenerClientes();
	}
	
	@GetMapping(path = "/custom/{id}")
	public ResponseEntity<?> getCustom(@PathVariable Integer id) {
		return customerService.obtenerCliente(id);
	}
	
	@PutMapping(path = "/custom")
	public ResponseEntity<?> putCustom(@RequestBody Customer custom) {
		return customerService.actualizarCliente(custom);
	}
	
	@DeleteMapping(path = "/custom/{id}")
	public ResponseEntity<?> delCustom(@PathVariable Integer id) {
		return customerService.borrarCliente(id);
	}
	
//	//Añadir producto al cliente
//		@PutMapping(path = "/custom/{cusId}&&{prdId}")
//		public ResponseEntity<?> putCusPrd(@PathVariable int cusId, @PathVariable int prdId) {
//			ResponseEntity<?> res = null;
//			Product p1 = s.getProducts().stream().filter(p -> p.getPrdId() == prdId).findFirst().orElse(null);
//			Customer c1 = s.getCustomers().stream().filter(c -> c.getCusId() == cusId).findFirst().orElse(null);
//					
//			if (s.getProducts() == null || s.getProducts().isEmpty()) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen productos");
//			} else if (s.getEmployees() == null || s.getEmployees().isEmpty()) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen empleados");
//			} else if (p1 == null) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
//			} else if (c1 == null) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado");
//			} else {
//				c1.addCusProduct(p1);
//				res = ResponseEntity.status(HttpStatus.OK).body(c1);
//			}
//					
//			return res;
//		}
//		
//		//Borrar producto al empleado
//		@DeleteMapping(path = "/custom/{cusId}&&{prdId}")
//		public ResponseEntity<?> delEmpPrd(@PathVariable int cusId, @PathVariable int prdId) {
//			ResponseEntity<?> res = null;
//			Product p1 = s.getProducts().stream().filter(p -> p.getPrdId() == prdId).findFirst().orElse(null);
//			Customer c1 = s.getCustomers().stream().filter(c -> c.getCusId() == cusId).findFirst().orElse(null);
//			Product ep1 = c1.getCusProducts().stream().filter(ep -> ep.getPrdId() == prdId).findFirst().orElse(null);
//			
//			if (s.getProducts() == null || s.getProducts().isEmpty()) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen productos");
//			} else if (s.getEmployees() == null || s.getEmployees().isEmpty()) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen cliente");
//			} else if (p1 == null) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
//			} else if (c1 == null) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el cliente");
//			} else if (ep1 == null) {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("El empleado no desarrolló dicho producto");
//			} else {
//				c1.rmCusProduct(ep1);
//				res = ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(c1, ep1));
//			}
//			
//			return res;
//		}
}
