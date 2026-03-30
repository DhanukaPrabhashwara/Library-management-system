package com.library.reservationservice.service;

import com.library.reservationservice.dto.ReservationRequest;
import com.library.reservationservice.model.Reservation;
import com.library.reservationservice.model.ReservationStatus;
import com.library.reservationservice.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setUserId(request.getUserId());
        reservation.setBookId(request.getBookId());
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setReservationDate(LocalDateTime.now());

        return repository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);
        return repository.save(reservation);
    }

    public Reservation createWaitlistReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setUserId(request.getUserId());
        reservation.setBookId(request.getBookId());
        reservation.setStatus(ReservationStatus.WAITLISTED);
        reservation.setReservationDate(LocalDateTime.now());

        return repository.save(reservation);
    }
}