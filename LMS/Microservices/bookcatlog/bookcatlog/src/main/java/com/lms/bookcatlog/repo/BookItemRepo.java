package com.lms.bookcatlog.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.bookcatlog.entity.BookItem;

@Repository
public interface BookItemRepo extends JpaRepository<BookItem, Long> {
	// Find all BookItems of a specific Book
    List<BookItem> findByBookId(Long bookId);
    
    // Find all BookItems by status (e.g., AVAILABLE, CHECKED_OUT)
    List<BookItem> findByStatus(String status);
}
