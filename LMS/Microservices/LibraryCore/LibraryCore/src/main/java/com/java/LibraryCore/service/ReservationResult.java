package com.java.LibraryCore.service;

import java.util.Optional;

public class ReservationResult {

    public enum Status {
        CONFIRMED,
        WAITLISTED,
        ALREADY_RESERVED,
        INVALID_INPUT
    }

    private final Status status;
    private final Optional<Long> reservationId;

    private ReservationResult(Status status, Optional<Long> reservationId) {
        this.status = status;
        this.reservationId = reservationId;
    }

    public static ReservationResult confirmed(long id) {
        return new ReservationResult(Status.CONFIRMED, Optional.of(id));
    }
    public static ReservationResult waitlisted() {
        return new ReservationResult(Status.WAITLISTED, Optional.empty());
    }
    public static ReservationResult alreadyReserved() {
        return new ReservationResult(Status.ALREADY_RESERVED, Optional.empty());
    }
    public static ReservationResult invalidInput() {
        return new ReservationResult(Status.INVALID_INPUT, Optional.empty());
    }

    public Status status() { return status; }
    public Optional<Long> reservationId() { return reservationId; }
}
