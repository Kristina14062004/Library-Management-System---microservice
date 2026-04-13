
package com.lms.bookcatlog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.bookcatlog.entity.BookItem;
import com.lms.bookcatlog.repo.BookItemRepo;
import com.lms.bookcatlog.service.BookItemService;

@Service
public class BookItemImpl implements BookItemService {
	@Autowired
    private BookItemRepo repository;
	
	@Override
	public List<BookItem> getAllBookItems() {
		return repository.findAll();
	}

	@Override
	public BookItem getBookItemById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public BookItem saveBookItem(BookItem bookItem) {
		 return repository.save(bookItem);
	}

	@Override
	public void deleteBookItem(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<BookItem> getBookItemsByBookId(Long bookId) {
		return repository.findByBookId(bookId);
	}

	@Override
	public List<BookItem> getBookItemsByStatus(String status) {
		return repository.findByStatus(status);
	}
 }