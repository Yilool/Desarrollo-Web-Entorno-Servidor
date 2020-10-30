package com.empresa.service;

import com.empresa.entity.Product;
import com.empresa.repository.ProductRepository;

import jdk.internal.net.http.Response;

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

        if (productRepository.existById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(productRepository.findProductById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarProducto(Product p) {
        ResponseEntity<?> res = null;

        if (productRepository.existById(p.getPrdId())) {
            Product p1 = productRepository.findProductById(p.getPrdId());
            p1.setPrdName(p.getPrdName());
            p1.setPrdPrice(p.getPrdPrice());
            res = ResponseEntity.status(HttpStatus.OK).body(p1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+p.getPrdId());
        }

        return res;
    }

    public ResponseEntity<?> borrarProducto(Integer id) {
        ResponseEntity<?> res = null;

        if (productRepository.existById(id)) {
            productRepository.deleteById(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado el producto con id: "+id);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+id);
        }

        return res;
    }
}
