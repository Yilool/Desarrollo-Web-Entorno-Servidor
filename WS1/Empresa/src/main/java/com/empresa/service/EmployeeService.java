package com.empresa.service;

import com.empresa.entity.Employee;
import com.empresa.repository.EmployeeRepository;
import com.empresa.repository.ProductRepository;

import jdk.internal.net.http.Response;
import jdk.internal.net.http.hpack.IntegerReader;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> crearEmpleado(Employee e) {
        ResponseEntity<?> res = null;

        e.getEmpProducts().forEach(p -> productRepository.save(p));
        employeeRepository.save(e);
        res = ResponseEntity.status(HttpStatus.OK).body(e);

        return res;
    }

    public ResponseEntity<?> obtenerEmpleados() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAll());
    }

    public ResponseEntity<?> obtenerEmpleado(Integer id) {
        ResponseEntity<?> res = null;

        if (employeeRepository.existById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findEmployeeById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra empleado con id: "+id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarEmpleado(Employee e) {
        ResponseEntity<?> res = null;

        if (employeeRepository.existById(e.getEmpId())) {
            Employee e1 = employeeRepository.findEmployeeById(e.getEmpId());
            e1.setEmpName(e.getEmpName());
            e1.setEmpSurname(e.getEmpSurname());
            res = ResponseEntity.status(HttpStatus.OK).body(e1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra empleado con id: "+e.getEmpId());
        }

        return res;
    }

    public ResponseEntity<?> borrarEmpleado(Integer id) {
        ResponseEntity<?> res = null;

        if (employeeRepository.existById(id)) {
            employeeRepository.deleteById(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado el empleado con id: "+id);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra empleado con id: "+id);
        }

        return res;
    }
    /*No se sabe*/
    public ResponseEntity<?> añadirProducto(Integer empleadoID, Integer productoID) {
        ResponseEntity<?> res = null;

        if (employeeRepository.existById(empleadoID)) {
            if (productRepository.existById(productoID)) {
                
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+productoID);
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra empleado con id: "+empleadoID);
        }

        return res;
    }

    public ResponseEntity<?> borrarProducto(Integer empleadoID, Integer productoID) {
        ResponseEntity<?> res = null;

        if (employeeRepository.existById(empleadoID)) {
            if (productRepository.existById(productoID)) {
                
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra producto con id: "+productoID);
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra empleado con id: "+empleadoID);
        }

        return res;
    }
}
