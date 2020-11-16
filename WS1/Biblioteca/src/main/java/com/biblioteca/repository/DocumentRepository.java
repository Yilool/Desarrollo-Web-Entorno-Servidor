package com.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer>{
	public Document findDocumentById(Integer id);
}
