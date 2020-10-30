package com.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empresa.entity.Product;
import com.empresa.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> crearProducto(Product p) {
        ResponseEntity<?> res = null;

        productRepository.save(p);
        res = ResponseEntity.status(HttpStatus.OK).body(p);

        return res;
    }

    public ResponseEntity<?> obtenerProductos() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<?> obtenerProducto(Integer id) {
        ResponseEntity<?> res = null;

        if (productRepository.existsById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(productRepository.findProductById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarProducto(Product p) {
        ResponseEntity<?> res = null;

        if (productRepository.existsById(p.getId())) {
            Product p1 = productRepository.findProductById(p.getId());
            p1.setPrdName(p.getPrdName());
            p1.setPrdPrice(p.getPrdPrice());
            productRepository.save(p1);
            res = ResponseEntity.status(HttpStatus.OK).body(p1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+p.getId());
        }

        return res;
    }

    public ResponseEntity<?> borrarProducto(Integer id) {
        ResponseEntity<?> res = null;

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado el producto con id: "+id);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
        }

        return res;
    }
}
