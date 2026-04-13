package com.java.LibraryCore.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checkouts")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique checkout record ID
    
    @Column(name = "user_id")
    private Integer userId; // ID of the User who checked out
    
    @Column(name = "book_id")
    private Integer bookId; // ID of the Book (could be linked to Book Catalog)
    
    private LocalDate checkoutDate; // Date when the book was borrowed
    
    private LocalDate dueDate; // Date when the book should be returned
    
    private LocalDate returnDate; // Date when the book was actually returned (nullable)
    
    @Enumerated(EnumType.STRING)
    private CheckoutStatus status; // e.g., BORROWED, RETURNED, OVERDUE
    
    public enum CheckoutStatus {
        BORROWED,
        RETURNED,
        OVERDUE
    }

    public Checkout() { }

    public Checkout(Integer userId, Integer bookId, LocalDate checkoutDate, LocalDate dueDate, CheckoutStatus status) {
        this.userId = userId;
        this.bookId = bookId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public CheckoutStatus getStatus() {
        return status;
    }

    public void setStatus(CheckoutStatus status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Checkout [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", checkoutDate=" + checkoutDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + ", getId()=" + getId()
				+ ", getUserId()=" + getUserId() + ", getBookId()=" + getBookId() + ", getCheckoutDate()="
				+ getCheckoutDate() + ", getDueDate()=" + getDueDate() + ", getReturnDate()=" + getReturnDate()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Checkout(Integer id, Integer userId, Integer bookId, LocalDate checkoutDate, LocalDate dueDate,
			LocalDate returnDate, CheckoutStatus status) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
    
    
}    

