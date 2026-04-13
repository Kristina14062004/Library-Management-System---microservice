package com.lms.bookcatlog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_items")
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many BookItems belong to one Book
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Status of the book copy: AVAILABLE, CHECKED_OUT, RESERVED, LOST, etc.
    @Column(length = 20)
    private String status;

    // Constructors
    public BookItem() {
    	super();
    }

    public BookItem(Book book, String status) {
        this.book = book;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", book=" + (book != null ? book.getTitle() : "null") +
                ", status='" + status + '\'' +
                '}';
    }
}