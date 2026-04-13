
package com.java.LibraryCore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "reservations",
    indexes = {
        @Index(name = "idx_res_user", columnList = "user_id"),
        @Index(name = "idx_res_book", columnList = "book_id"),
        @Index(name = "idx_res_user_book_status", columnList = "user_id, book_id, status")
    }
)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ReservationStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public enum ReservationStatus {
        ACTIVE,
        WAITLISTED,
        FULFILLED,
        CANCELED
    }

    public Reservation() {}

    public Reservation(Integer userId, Integer bookId, LocalDateTime reservationDate, ReservationStatus status) {
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.reservationDate == null) {
            this.reservationDate = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters
    public Integer getId() { return id; }
    public Integer getUserId() { return userId; }
    public Integer getBookId() { return bookId; }
    public LocalDateTime getReservationDate() { return reservationDate; }
    public ReservationStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Long getVersion() { return version; }

    public void setId(Integer id) { this.id = id; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
    public void setStatus(ReservationStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setVersion(Long version) { this.version = version; }
}

