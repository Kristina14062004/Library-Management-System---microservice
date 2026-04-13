package com.java.LibraryCore.repo;

import com.java.LibraryCore.entity.Reservation;
import com.java.LibraryCore.entity.Reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    
    List<Reservation> findByBookIdAndStatusInOrderByReservationDateAsc(Integer bookId, List<ReservationStatus> statuses);

    boolean existsByUserIdAndBookIdAndStatusIn(Integer userId, Integer bookId, List<ReservationStatus> statuses);

    long countByBookIdAndStatus(Integer bookId, ReservationStatus status);
}
