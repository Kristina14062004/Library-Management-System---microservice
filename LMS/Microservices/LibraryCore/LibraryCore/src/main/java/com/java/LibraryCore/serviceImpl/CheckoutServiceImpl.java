package com.java.LibraryCore.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.java.LibraryCore.dto.BookCopyDTO;
import com.java.LibraryCore.dto.BookDTO;
import com.java.LibraryCore.dto.CheckoutRequestDTO;
import com.java.LibraryCore.dto.UserDTO;
import com.java.LibraryCore.entity.Checkout;
import com.java.LibraryCore.entity.Checkout.CheckoutStatus;
import com.java.LibraryCore.repo.CheckoutRepository;
import com.java.LibraryCore.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    
    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);
    private static final int MAX_CHECKOUTS = 5;
    private static final int MAX_DAYS = 10;
    private static final String USER_SERVICE_URL = "http://localhost:8181";
    private static final String BOOK_SERVICE_URL = "http://localhost:8080";
    

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String checkoutBook(CheckoutRequestDTO request) {
        final Integer userId = request.getUserId(); // immutable variable
        final Integer bookId = request.getBookId();
        logger.info("Starting checkout for userId={} with bookId={}", userId, bookId);
        
        // 1. Verify user exists in User microservice
        try {
            restTemplate.getForEntity(USER_SERVICE_URL + "/user/{userId}", UserDTO.class, userId);
            logger.info("User {} verified in User Service.", userId);
        } catch (HttpClientErrorException.NotFound e) {
            logger.warn("User not found: {}", userId);
            return "User not found.";
        } catch (Exception e) {
            logger.error("Error contacting User service for userId={}", userId, e);
            return "Error contacting User service.";
        }

        // 2. Check how many active checkouts the user has
        List<Checkout> userCheckouts = checkoutRepository.findByUserId(userId).stream()
                .filter(co -> co.getStatus() != CheckoutStatus.RETURNED)
                .collect(Collectors.toList());
        logger.info("User {} has {} active checkouts.", userId, userCheckouts.size());
        if (userCheckouts.size() >= MAX_CHECKOUTS) {
            logger.info("User {} reached max checkout limit.", userId);
            return "Max checkout limit reached.";
        }

        // 3. Get book info from Book Catalog service
        ResponseEntity<BookDTO> bookResponse;
        System.out.println("Calling Book Service for bookId: " + bookId);
        System.out.println("Using bookId: " + bookId);
        System.out.println("Request URL: " + BOOK_SERVICE_URL + "/api/books/" + bookId);

        try {
        	
            bookResponse = restTemplate.getForEntity(BOOK_SERVICE_URL + "/api/books/{bookId}", BookDTO.class, bookId);
            if (!bookResponse.getStatusCode().is2xxSuccessful() || bookResponse.getBody() == null) {
                logger.warn("Book not found or invalid response for bookId={}", bookId);
                return "Book not found.";
            }
        } catch (Exception e) {
            logger.error("Error contacting Book Service for bookId={}", bookId, e);
            return "Book not found.";
        }

        BookDTO book = bookResponse.getBody();

        // Use the ID from the Book DTO (ensures correct linkage)
        Integer actualBookId = book.getId();

        // 4. Find an available copy
//        BookCopyDTO availableCopy = null;
//        for (BookCopyDTO copy : book.getCopies()) {
//            if (copy.isAvailable()) {
//                availableCopy = copy;
//                break;
//            }
//        }
//        if (availableCopy == null) {
//            logger.info("No available copies for bookId={}", bookId);
//            return "No copies available.";
//        }

//        // 5. Mark copy as unavailable (update in Book Catalog)
//        try {
//            restTemplate.put(BOOK_SERVICE_URL + "/api/bookcopies/{copyId}/markUnavailable", null, availableCopy.getCopyId());
//            logger.info("Book copy {} marked as unavailable.", availableCopy.getCopyId());
//        } catch (Exception e) {
//            logger.error("Failed to mark copy unavailable for copyId={}", availableCopy.getCopyId(), e);
//            return "Failed to update copy availability.";
//        }

        // 6. Save checkout info locally
        final Checkout checkout = new Checkout();
        checkout.setUserId(userId);
        checkout.setBookId(actualBookId);
        checkout.setCheckoutDate(LocalDate.now());
        checkout.setDueDate(LocalDate.now().plusDays(MAX_DAYS));
        checkout.setStatus(CheckoutStatus.BORROWED);
        checkoutRepository.save(checkout);
        logger.info("Checkout record saved for userId={}, bookId={}", userId);  //, availableCopy.getBookId()

        return "Checkout successful.";
    }
}