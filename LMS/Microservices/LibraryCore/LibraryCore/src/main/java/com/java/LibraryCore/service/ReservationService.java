package com.java.LibraryCore.service;

public interface ReservationService {
    ReservationResult makeReservation(Integer userId, Integer bookId);
}

