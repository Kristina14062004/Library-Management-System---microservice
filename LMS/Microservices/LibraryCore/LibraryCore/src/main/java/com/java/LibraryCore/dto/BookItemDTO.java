package com.java.LibraryCore.dto;

public class BookItemDTO {
	private Integer id;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BookItemDTO(Integer id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	@Override
	public String toString() {
		return "BookItemDTO [id=" + id + ", status=" + status + "]";
	}
	public BookItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
