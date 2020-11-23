package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.biblioteca.entity.Book;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.LibraryRepository;
import com.biblioteca.repository.PageRepository;
import com.biblioteca.service.BookService;

class GeBookTest {
	//Subject Under Test
	private BookService sut;
	//Dependencias
	private BookRepository mockBookRepository;
	private PageRepository mockPageRepository;
	
	private Book mockBook;
	
	@BeforeEach
	private void init() {
		sut = new BookService();
		mockPageRepository = mock(PageRepository.class);
		mockBookRepository = mock(BookRepository.class);
		
		sut.setBookRepository(mockBookRepository);
		sut.setPageRepository(mockPageRepository);
	}
	
	@Test
	public void obtenerLibroExiste() {
		mockBook = mock(Book.class);
		Mockito.when(mockBookRepository.existsById(3));
		ResponseEntity<?> res = ResponseEntity.status(HttpStatus.OK).body(mockBook);

		sut.obtenerLibro(3);
		assert(mockResponse == sut.obtenerLibro(3));
	}
	
//	public ResponseEntity<?> obtenerLibro(Integer id) {
//		ResponseEntity<?> res = null;
//
//		if (bookRepository.existsById(id)) {
//			res = ResponseEntity.status(HttpStatus.OK).body(bookRepository.findBookById(id));
//		} else {
//			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + id);
//		}
//
//		return res;
//	}
	@Test
	public void obtenerLibroNoExiste() {
		mockBook = mock(Book.class);
		Mockito.when(mockBookRepository.existsById(3));
		Mockito.when(mockResponse.status(HttpStatus.OK).body("No se encuentra libro con id: " + 3));

		sut.obtenerLibro(3);
		assert(mockResponse == sut.obtenerLibro(3));
	}

}
