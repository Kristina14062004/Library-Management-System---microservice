package com.java.LibraryCore.dto;

import java.util.List;

public class BookDTO {
	
	private Integer id;
	private String title;
	private String author;
	
	private List<BookCopyDTO> copies;
	public List<BookCopyDTO> getCopies() {
        return copies;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BookDTO(Integer id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}


	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
	

}
