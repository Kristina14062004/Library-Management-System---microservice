package com.lms.bookcatlog.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.bookcatlog.entity.Book;

@Repository
public interface BookCatlogRepo extends JpaRepository<Book,Long> {

	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findBySubjectCategoryContainingIgnoreCase(String subject);
    List<Book> findByPublicationDate(LocalDate publicationDate);
}
