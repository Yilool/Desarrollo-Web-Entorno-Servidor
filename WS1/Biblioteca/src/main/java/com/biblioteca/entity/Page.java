package com.biblioteca.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "BookId")
	private Book book;
	
	public Page() {
	}
	
	public Page(int number, Book book) {
		this.number = number;
		this.book = book;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(Page other) {

		return Integer.valueOf(this.getId()).compareTo(other.getId());
	}
}
