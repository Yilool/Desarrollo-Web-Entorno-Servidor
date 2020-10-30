package com.empresa.service;

import com.empresa.entity.Customer;
import com.empresa.repository.CustomerRepository;
import com.empresa.repository.ProductRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> crearCliente(Customer c) {
        ResponseEntity<?> res = null;

        c.getCusProducts().forEach(p -> productRepository.save(p));
        customerRepository.save(c);
        res = ResponseEntity.status(HttpStatus.OK).body(c);

        return res;
    }

    public ResponseEntity<?> obtenerClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
    }

    public ResponseEntity<?> obtenerCliente(Integer id) {
        ResponseEntity<?> res = null;

        if (customerRepository.existById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(customerRepository.findCustomerById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarCliente(Customer c) {
        ResponseEntity<?> res = null;

        if (customerRepository.existById(c.getCusId())) {
            Customer c1 = customerRepository.findCustomerById(c.getCusId());
            c1.setCusName(c.getCusName());
            c1.setCusSurname(c.getCusSurname());
            res = ResponseEntity.status(HttpStatus.OK).body(c1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+c.getCusId());
        }

        return res;
    }

    public ResponseEntity<?> borrarCliente(Integer id) {
        ResponseEntity<?> res = null;

        if (customerRepository.existById(id)) {
            customerRepository.deleteById(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado el cliente con id: "+id);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+id);
        }

        return res;
    }
    /*No se sabe*/
    public ResponseEntity<?> añadirProducto(Integer clienteID, Integer productoID) {
        ResponseEntity<?> res = null;

        if (customerRepository.existById(clienteID)) {
            if (productRepository.existById(productoID)) {
                
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+productoID);
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+clienteID);
        }

        return res;
    }

    public ResponseEntity<?> borrarProducto(Integer clienteID, Integer productoID) {
        ResponseEntity<?> res = null;

        if (customerRepository.existById(clienteID)) {
            if (productRepository.existById(productoID)) {
                
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+productoID);
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+clienteID);
        }

        return res;
    }
}
