package com.lms.bookcatlog.service;

import java.time.LocalDate;
import java.util.List;
import com.lms.bookcatlog.entity.Book;

public interface BookCatlogService {
	 List<Book> getAllBooks();
	 Book saveBook(Book book);
	 List<Book> findByTitleContainingIgnoreCase(String title);
	 List<Book> findByAuthorContainingIgnoreCase(String author);
	 List<Book> findBySubjectCategoryContainingIgnoreCase(String subject);
     List<Book> findByPublicationDate(LocalDate publicationDate);
}
