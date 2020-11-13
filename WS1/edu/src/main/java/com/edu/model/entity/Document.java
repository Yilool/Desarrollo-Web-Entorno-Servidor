package com.edu.model.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Blob doc;
	private String fileName;
	private Integer fileSize;

	
	public Document() {
	}
	
	public Document(Blob pic, String name, Integer size) {
		this.doc = pic;
		this.fileName = name;
		this.fileSize = size;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blob getPicture() {
		return doc;
	}

	public void setPicture(Blob picture) {
		this.doc = picture;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	
}
