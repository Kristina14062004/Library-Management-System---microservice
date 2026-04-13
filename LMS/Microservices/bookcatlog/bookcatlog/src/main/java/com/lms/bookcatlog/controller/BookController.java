package com.lms.bookcatlog.controller;

import com.lms.bookcatlog.entity.Book;
import com.lms.bookcatlog.service.BookCatlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookCatlogService bookService;

    // Get all books
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Add a new book
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    // Search books by title
    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestParam("title") String title) {
        return bookService.findByTitleContainingIgnoreCase(title);
    }

    // Search books by author
    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestParam("author") String author) {
        return bookService.findByAuthorContainingIgnoreCase(author);
    }

    // Search books by subject
    @GetMapping("/search/subject")
    public List<Book> searchBySubject(@RequestParam("subject") String subject) {
        return bookService.findBySubjectCategoryContainingIgnoreCase(subject);
    }

    // Search books by publication date
    @GetMapping("/search/date")
    public List<Book> searchByPublicationDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return bookService.findByPublicationDate(date);
    }
}
