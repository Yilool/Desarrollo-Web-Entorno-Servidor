package com.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        c.getProducts().forEach(p -> productRepository.save(p));
        customerRepository.save(c);
        res = ResponseEntity.status(HttpStatus.OK).body(c);

        return res;
    }

    public ResponseEntity<?> obtenerClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
    }

    public ResponseEntity<?> obtenerCliente(Integer id) {
        ResponseEntity<?> res = null;

        if (customerRepository.existsById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(customerRepository.findCustomerById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarCliente(Customer c) {
        ResponseEntity<?> res = null;

        if (customerRepository.existsById(c.getId())) {
            Customer c1 = customerRepository.findCustomerById(c.getId());
            c1.setName(c.getName());
            c1.setSurname(c.getSurname());
            customerRepository.save(c1);
            res = ResponseEntity.status(HttpStatus.OK).body(c1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+c.getId());
        }

        return res;
    }

    public ResponseEntity<?> borrarCliente(Integer id) {
        ResponseEntity<?> res = null;

        if (customerRepository.existsById(id)) {
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

        if (customerRepository.existsById(clienteID)) {
            if (productRepository.existsById(productoID)) {
                
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

        if (customerRepository.existsById(clienteID)) {
            if (productRepository.existsById(productoID)) {
                
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+productoID);
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra cliente con id: "+clienteID);
        }

        return res;
    }
}
