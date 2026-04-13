package com.lms.bookcatlog.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.bookcatlog.entity.Book;
import com.lms.bookcatlog.repo.BookCatlogRepo;
import com.lms.bookcatlog.service.BookCatlogService;

@Service
public class BookCatlogImpl implements BookCatlogService {

	@Autowired
	private BookCatlogRepo bookRepo;
	
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	@Override
	public List<Book> findByTitleContainingIgnoreCase(String title) {
		// TODO Auto-generated method stub
		return bookRepo.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<Book> findByAuthorContainingIgnoreCase(String author) {
		// TODO Auto-generated method stub
		return bookRepo.findByAuthorContainingIgnoreCase(author);
	}

	@Override
	public List<Book> findBySubjectCategoryContainingIgnoreCase(String subject) {
		// TODO Auto-generated method stub
		return bookRepo.findBySubjectCategoryContainingIgnoreCase(subject);
	}

	@Override
	public List<Book> findByPublicationDate(LocalDate publicationDate) {
		// TODO Auto-generated method stub
		return bookRepo.findByPublicationDate(publicationDate);
	}
  
}
