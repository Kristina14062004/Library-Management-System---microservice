
package com.lms.bookcatlog.entity;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    // Book title
    @Column(name = "book_title")
    private String title;

    // Author(s) name (could be a string or a separate entity if multi-author support needed)
    @Column(name = "book_author")
    private String author;

    // Subject category for classification
    @Column
    private String subjectCategory;

    // Publication date of the book
    @Column
    private LocalDate publicationDate;

    // Default constructor
    public Book() {
    	super();
    }

    // Constructor with parameters (optional)
    public Book(String title, String author, String subjectCategory, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.subjectCategory = subjectCategory;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSubjectCategory() {
        return subjectCategory;
    }

    public void setSubjectCategory(String subjectCategory) {
        this.subjectCategory = subjectCategory;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Optionally, override toString() method
    @Override
    public String toString() {
        return "Book{" +
        		"id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", subjectCategory='" + subjectCategory + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}