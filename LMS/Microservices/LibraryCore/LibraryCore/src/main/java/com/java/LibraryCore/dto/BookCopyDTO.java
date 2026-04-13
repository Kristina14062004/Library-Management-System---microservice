package com.java.LibraryCore.dto;

public class BookCopyDTO {
	private Integer copyId;
    private Integer bookId;
    private boolean available; 
    
    
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
	public Integer getCopyId() {
		return copyId;
	}
	public void setCopyId(Integer copyId) {
		this.copyId = copyId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "BookCopyDTO [copyId=" + copyId + ", bookId=" + bookId + ", available=" + available + "]";
	}
	public BookCopyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookCopyDTO(Integer copyId, Integer bookId, boolean available) {
		super();
		this.copyId = copyId;
		this.bookId = bookId;
		this.available = available;
	}
    
    
}
