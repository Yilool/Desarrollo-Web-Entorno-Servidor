package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Page;
import com.biblioteca.service.PageService;

@RestController
@RequestMapping(path = "/biblioteca")
public class PageController {
	@Autowired
	private PageService pageService;
	
	@PostMapping(path = "/page")
	public ResponseEntity<?> postPage(@RequestBody Page product) {
		return pageService.crearPagina(product);
	}
	
	@GetMapping(path = "/page")
	public ResponseEntity<?> getAllPages() {
		return pageService.obtenerPaginas();
	}
	
	@GetMapping(path = "/page/{id}")
	public ResponseEntity<?> getPage(@PathVariable Integer id) {
		return pageService.obtenerPagina(id);
	}
	
	@PutMapping(path = "/page")
	public ResponseEntity<?> putPage(@RequestBody Page page) {
		return pageService.actualizarPagina(page);
	}
	
	@DeleteMapping(path = "/page/{id}")
	public ResponseEntity<?> delPage(@PathVariable Integer id) {
		return pageService.borrarPagina(id);
	}
}
