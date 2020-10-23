package com.jacaranda.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.SuscriptionEnum;
import com.jacaranda.entity.Visual;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.repository.VisualRepository;

/**
 * Controller to manage customer related requests
 * @author raul
 */

@RestController
@RequestMapping(path = "/netflix")
public class CustomerController {
	
	@Autowired
	private VisualRepository visualRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	
	@SuppressWarnings("serial")
	private List<Customer> customers = new ArrayList<>() {
		{
			add(new Customer("Alvaro", "Sánchez", "Sevilla", "11111111A", SuscriptionEnum.BASIC));
			add(new Customer("Yi", "Chen", "Sevilla", "22222222B", SuscriptionEnum.PREMIUM));
			add(new Customer("Cliente", "Largo", LocalDate.of(1992, 10, 22), "address", "city", "dni", "country", "666444999", "Masculino", SuscriptionEnum.BASIC));
			add(new Customer("Raul", "Morales", "Sevilla", "33333333C", SuscriptionEnum.PREMIUM));
		}
	};


	

	/**
	 * Método que comprueba si el cliente existe, introduciendo el nombre.	
	 * @param newName (Nombre del cliente)
	 * @return int (id del cliente)
	 */
	private int existeCliente(String newName) {
		boolean customerEncontrado = false;
		int id = -1;

		for (int i = 0; i < customers.size() && customerEncontrado == false; i++) {
			String name = customers.get(i).getName();
			
			if (newName.equalsIgnoreCase(name)) {
				customerEncontrado = true;	
				id = customers.get(i).getId();
			} 
		}

		return id;
	}

	
	
	// ------------------------------------------------ CRUD CUSTOMER ------------------------------------------------
	
	/**
	 * GET. Método para revisar el listado de clientes existentes.
	 * @return
	 */
	@GetMapping("/customer")
	public ResponseEntity<?> leeClientes() {
		ResponseEntity<?> response = null;
		
		if (customers.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(customers);
		}
		
//		return response;
		return ResponseEntity.ok(customerRepository.findAll());
	}

	
	
	/**
	 * POST. Creación de cliente, proporcionando JSON en body
	 * @param nuevoCliente
	 * @return
	 */
//	@PostMapping("/customer")
//	public ResponseEntity<?> creaCliente(@RequestBody Customer nuevo) {		
//		ResponseEntity<?> response = null;
//		
//		// guardamos el nombre del nuevo cliente
//		String newCustomer = nuevo.getName();
//		
//		// Si el cliente no existe en la lista de clientes...
//		if (existeCliente(newCustomer) == -1) {
//			//obtenemos el id del último cliente
//			int id = customers.get(customers.size() - 1).getId();
//			
//			//incrementamos el anterior id y lo aplicamos al nuevo cliente
//			nuevo.setId(id + 1);
//			nuevo.setVisuals(new ArrayList<Visual>());
//			
//			//insertamos el cliente en la lista de clientes.
//			customers.add(nuevo);
//			response = ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
//			
//		// Si el cliente existe en la lista de clientes.
//		} else {
//			response = ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR. El cliente " + newCustomer + " ya existe. No se puede crear");
//		}
//		
//		return response;
//	}
	
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	
	
	/**
	 * PUT. Modificación de cliente, proporcionando nombre
	 * @param oldName
	 * @param newName
	 * @return
	 */
	@PutMapping(path="/customer")
	public ResponseEntity<?> modCli(@RequestParam String oldName, @RequestParam String newName)  {
		
		ResponseEntity<?> response = null;
		boolean custEncontrado = false;
		
		for (int i = 0; i < customers.size() && custEncontrado == false; i++) {
			String nombre = customers.get(i).getName();
			
			if (nombre.equalsIgnoreCase(oldName)) {
				custEncontrado = true;
				
				customers.get(i).setName(newName);
				
				response = ResponseEntity.status(HttpStatus.OK).body(customers);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Cliente no encontrado.");
			}
		}
		
		return response;
	}

	
	
	/**
	 * DELETE. Eliminación de cliente, proporcionando nombre
	 * @param name
	 * @return
	 */
	@DeleteMapping(path="/customer")
	public ResponseEntity<?> borraCli(@RequestParam String name) {
		
		ResponseEntity<?> response = null;
		boolean custEncontrado = false;
		
		for (int i = 0; i < customers.size() && custEncontrado == false; i++) {
			String nombre = customers.get(i).getName();
			
			if (nombre.equalsIgnoreCase(name)) {
				custEncontrado = true;
				customers.remove(customers.get(i));
				
				response = ResponseEntity.status(HttpStatus.OK).body(customers);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Cliente no encontrado.");
			}
		}
		
		return response;
	}
	// ------------------------------------------------ CRUD CUSTOMER ------------------------------------------------
	
	
	
	// ------------------------------------------------- CRUD VISUAL -------------------------------------------------
	/**
	 * GET. Método para revisar el listado de visualizaciones de un cliente.
	 * @return
	 */
//	@GetMapping("/visual")
//	public ResponseEntity<?> leeVisualCliente(@RequestParam String c) {
//		
//		ResponseEntity<?> response = null;
//
//		// guardamos el id de cliente (si no existe, será -1)
//		int idCust = existeCliente(c);
//
//		// Si el cliente no existe en la lista de clientes...
//		if (idCust == -1) {
//			
//			response = ResponseEntity.status(HttpStatus.NOT_FOUND).
//					body("ERROR. El cliente " + c + " no existe. No se puede mostrar la lista de visualizaciones");
//			
//			// Si el cliente existe en la lista de clientes.
//		} else {
//			
//			// Si el cliente tiene alguna visualización, se muestra el listado
//			if (customers.get(idCust).contadorVisuals() > 0) {
//				response = ResponseEntity.status(HttpStatus.OK).body(customers.get(idCust).getVisuals());
//				
//			// si el cliente no tiene visualizaciones, se muestra el error.
//			} else {
//				response = ResponseEntity.status(HttpStatus.NOT_FOUND).
//						body("ERROR. El cliente " + c + " todavía no tiene visualizaciones.");
//			}
//			
//		}
//		
//		return response;
//	}
	
	@GetMapping("/visual")
	public ResponseEntity<?> leeVisualCliente() {
		return ResponseEntity.ok(visualRepository.findAll());
	}
	
	
	
	
	/**
	 * POST. Creación de visualización para un cliente, proporcionando JSON en body
	 * @param nuevoCliente
	 * @return
	 */
//	@PostMapping("/visual")
//	public ResponseEntity<?> creaVisualCliente(@RequestParam String c, @RequestBody Visual v) {
//		ResponseEntity<?> response = null;
//
//		// guardamos el id de cliente (si no existe, será -1)
//		int idCust = existeCliente(c);
//
//		// Si el cliente no existe en la lista de clientes...
//		if (idCust == -1) {
//
//			response = ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body("ERROR. El cliente " + c + " no existe. No se puede mostrar la lista de visualizaciones");
//
//		// Si el cliente existe en la lista de clientes.
//		} else {
//
//			// Se guarda el cliente (para ver el código más claro)
//			Customer cliente = customers.get(idCust);
//			
//			// Se calcula el id de visualización y se inserta a la nueva visualización.
//			int idVisual = cliente.contadorVisuals();
//			v.setIdVisual(idVisual);
//			
//			
//			// Comprobar si el producto existe.
//			if (v.getProducto().getIdProduct() >= 0 && v.getProducto().getIdProduct() <= ProductController.totalProductos()) {
//
//				
//				// Comprobar si la hora de inicio y fin son correctas.
//				if (v.getInicio() == null || v.getFin() == null) {
//					response = ResponseEntity.status(HttpStatus.NOT_FOUND)
//							.body("ERROR. La hora especificada no es correcta. No se puede añadir la visualización");
//					
//				// si la hora es correcta...
//				} else {
//					
//					// guardamos el tipo de suscripción del producto y del usuario
//					SuscriptionEnum suscripcionProducto = v.getProducto().getTipoSuscripcion();
//					SuscriptionEnum suscripcionUsuario = customers.get(idCust).getTipoSuscripcion();
//					
//					// si la suscripción del usuario es inferior a la del producto...
//					if (suscripcionUsuario.equals(SuscriptionEnum.BASIC) && suscripcionProducto.equals(SuscriptionEnum.PREMIUM)) {
//						response = ResponseEntity.status(HttpStatus.CONFLICT)
//								.body("ERROR. La suscripción del usuario no es suficiente para ver este contenido.");
//					} else {
//						// Insertar visualización a cliente
//						customers.get(idCust).addVisual(v);
//						response = ResponseEntity.status(HttpStatus.OK).body(customers.get(idCust).getVisuals());
//					}
//					
//				}
//				
//			// si el producto no existe, se muestra el error.
//			} else {
//				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
//						.body("ERROR. El producto " + v.getProducto().getIdProduct() + " no existe. No se puede añadir la visualización");
//			}
//
//			
//		}
//
//		return response;
//	}
	
	@PostMapping("/visual")
//	public ResponseEntity<?> creaVisualCliente(@RequestParam String c, @RequestBody Visual v) {
//	public Customer createCustomer(@RequestBody Customer customer) {
	public Visual createVisual(@RequestParam String c, @RequestBody Visual v) {
		return visualRepository.save(v);
	}
	// ------------------------------------------------- CRUD VISUAL -------------------------------------------------

	
}
