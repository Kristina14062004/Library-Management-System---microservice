package com.java.LibraryCore.serviceImpl;

import com.java.LibraryCore.entity.Reservation;
import com.java.LibraryCore.entity.Reservation.ReservationStatus;
import com.java.LibraryCore.repo.ReservationRepository;
import com.java.LibraryCore.service.ReservationResult;
import com.java.LibraryCore.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;

    // Base URL for catalog
    private final String catalogBaseUrl;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RestTemplate restTemplate,
                                  @Value("$http://BOOKCATALOG-SERVICE") String catalogBaseUrl) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
        this.catalogBaseUrl = catalogBaseUrl;
    }

    @Override
    @Transactional
    public ReservationResult makeReservation(Integer userId, Integer bookId) {
        // Validate inputs
        if (userId == null || userId <= 0 || bookId == null || bookId <= 0) {
            return ReservationResult.invalidInput();
        }

        
        boolean alreadyActive = reservationRepository.existsByUserIdAndBookIdAndStatusIn(
                userId, bookId, List.of(ReservationStatus.ACTIVE));
        if (alreadyActive) {
            return ReservationResult.alreadyReserved();
        }

        boolean available = isBookAvailable(bookId);

        Reservation res = new Reservation(
                userId,
                bookId,
                LocalDateTime.now(),
                available ? ReservationStatus.ACTIVE : ReservationStatus.WAITLISTED
        );

        res = reservationRepository.save(res);

        if (available) {
            return ReservationResult.confirmed(res.getId().longValue());
        } else {
            return ReservationResult.waitlisted();
        }
    }

    /**
     * Availability logic via Book Catalog service:
     * GET {catalogBaseUrl}/api/bookitems/byBook/{bookId}
     * Expect an array of items with "status" field; available if any item has status == "AVAILABLE"
     */
    private boolean isBookAvailable(Integer bookId) {
        String url = catalogBaseUrl + "/api/bookitems/byBook/{bookId}";

        try {
            BookItemDto[] items = restTemplate.getForObject(url, BookItemDto[].class, bookId);
            long availableCount = Arrays.stream(items != null ? items : new BookItemDto[0])
                    .filter(Objects::nonNull)
                    .filter(it -> "AVAILABLE".equalsIgnoreCase(it.getStatus()))
                    .count();

            log.info("BookId={} available copies={}", bookId, availableCount);
            return availableCount > 0;
        } catch (Exception e) {
            log.error("Error checking availability for bookId={} via {}", bookId, url, e);
            return false;
        }
    }

    //  DTO for catalog response
    public static class BookItemDto {
        private Long id;
        private String status;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
