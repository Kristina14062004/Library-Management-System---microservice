package com.java.LibraryCore.repo;

import java.time.LocalDate; 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.LibraryCore.entity.Checkout;
import com.java.LibraryCore.entity.Checkout.CheckoutStatus;


@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
	// Find all checkouts for a specific user
    List<Checkout> findByUserId(Integer userId);

    // Find all checkouts for a specific book
    List<Checkout> findByBookId(String bookId);

    // Find all checkouts that are overdue
    List<Checkout> findByDueDateBeforeAndStatus(LocalDate date, CheckoutStatus status);
}
