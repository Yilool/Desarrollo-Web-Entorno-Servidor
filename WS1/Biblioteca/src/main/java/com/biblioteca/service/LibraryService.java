package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Library;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private BookRepository bookRepository;

	public ResponseEntity<?> crearBiblioteca(Library l) {
		ResponseEntity<?> res = null;

		l.getBooks().forEach(p -> bookRepository.save(p));
		libraryRepository.save(l);
		res = ResponseEntity.status(HttpStatus.OK).body(l);

		return res;
	}

	public ResponseEntity<?> obtenerBibliotecas() {
		return ResponseEntity.status(HttpStatus.OK).body(libraryRepository.getAllOrderById());
	}

	public ResponseEntity<?> obtenerBiblioteca(Integer id) {
		ResponseEntity<?> res = null;

		if (libraryRepository.existsById(id)) {
			res = ResponseEntity.status(HttpStatus.OK).body(libraryRepository.findLibraryById(id));
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra biblioteca con id: " + id);
		}

		return res;
	}

	public ResponseEntity<?> actualizarBiblioteca(Library l) {
		ResponseEntity<?> res = null;

		if (libraryRepository.existsById(l.getId())) {
			Library l1 = libraryRepository.findLibraryById(l.getId());
			l1.setName(l.getName());
			libraryRepository.save(l1);
			res = ResponseEntity.status(HttpStatus.OK).body(l1);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra biblioteca con id: " + l.getId());
		}

		return res;
	}

	public ResponseEntity<?> borrarBiblioteca(Integer id) {
		ResponseEntity<?> res = null;

		if (libraryRepository.existsById(id)) {
			libraryRepository.deleteById(id);
			res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado biblioteca con id: " + id);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra biblioteca con id: " + id);
		}

		return res;
	}
	
	public ResponseEntity<?> añadirLibro(Integer libraryId, Integer bookId) {
		ResponseEntity<?> res = null;

		if (libraryRepository.existsById(libraryId)) {
			if (bookRepository.existsById(bookId)) {
				Book b = bookRepository.findBookById(bookId);
				Library l = libraryRepository.findLibraryById(libraryId);
				b.setLibrary(l);
				bookRepository.save(b);
				res = ResponseEntity.status(HttpStatus.OK).body(b);
			} else {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + bookId);
			}
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra biblioteca con id: " + libraryId);
		}

		return res;
	}

	public ResponseEntity<?> borrarLibro(Integer bookId) {
		ResponseEntity<?> res = null;
		
		if (bookRepository.existsById(bookId)) {
			Book b = bookRepository.findBookById(bookId);
			b.setLibrary(null);
			bookRepository.save(b);
			res = ResponseEntity.status(HttpStatus.OK).body(b);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + bookId);
		}

		return res;
	}
}
