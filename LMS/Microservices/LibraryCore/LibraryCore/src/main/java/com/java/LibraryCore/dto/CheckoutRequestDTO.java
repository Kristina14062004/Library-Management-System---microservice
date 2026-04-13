package com.java.LibraryCore.dto;

public class CheckoutRequestDTO {
    private Integer userId;
    private Integer bookId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public CheckoutRequestDTO(Integer userId, Integer bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
	}
	public CheckoutRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CheckoutRequestDTO [userId=" + userId + ", bookId=" + bookId + "]";
	}

}
