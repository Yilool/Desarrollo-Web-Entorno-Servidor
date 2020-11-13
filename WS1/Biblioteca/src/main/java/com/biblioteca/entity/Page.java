package com.biblioteca.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Pagina
 * 
 * @author yixiangch
 *
 */
@Entity
@Table(name = "Pages")
public class Page implements Comparable<Page>, Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Number")
	private int number;
	@JoinColumn(name = "BookId")
	private Integer book;
	@OneToMany(mappedBy = "page", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> documents;
	
	public Page() {
		this.documents = new ArrayList<>();
	}
	
	public Page(int number, Integer book) {
		this.number = number;
		this.book = book;
		this.documents = new ArrayList<>();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@Override
	public int compareTo(Page other) {

		return Integer.valueOf(this.getId()).compareTo(other.getId());
	}
}
