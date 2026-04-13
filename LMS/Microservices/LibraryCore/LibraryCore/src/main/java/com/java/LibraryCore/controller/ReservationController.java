
package com.java.LibraryCore.controller;

import com.java.LibraryCore.service.ReservationResult;
import com.java.LibraryCore.service.ReservationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@Validated
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationRequest request) {
        long t0 = System.nanoTime();
        log.info("Reservation request userId={}, bookId={}", request.getUserId(), request.getBookId());

        ReservationResult result = reservationService.makeReservation(request.getUserId(), request.getBookId());

        long ms = (System.nanoTime() - t0) / 1_000_000;
        log.info("Reservation result={} in {} ms for userId={}, bookId={}",
                result.status(), ms, request.getUserId(), request.getBookId());

        switch (result.status()) {
            case CONFIRMED -> {
                HttpHeaders headers = new HttpHeaders();
                result.reservationId().ifPresent(id -> headers.add(HttpHeaders.LOCATION, "/reservations/" + id));
                return new ResponseEntity<>("Reservation confirmed", headers, HttpStatus.CREATED);
            }
            case WAITLISTED -> {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Book unavailable, added to waitlist");
            }
            case ALREADY_RESERVED -> {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Already reserved");
            }
            case INVALID_INPUT -> {
                return ResponseEntity.badRequest().body("Invalid userId/bookId");
            }
            default -> {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Unexpected error");
            }
        }
    }

    // DTO with validation (POJO for Java 17)
    public static class ReservationRequest {
        @NotNull @Positive
        private Integer userId;
        @NotNull @Positive
        private Integer bookId;

        public Integer getUserId() { return userId; }
        public void setUserId(Integer userId) { this.userId = userId; }
        public Integer getBookId() { return bookId; }
        public void setBookId(Integer bookId) { this.bookId = bookId; }
    }
}
