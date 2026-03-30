package com.library.reservationservice.controller;

import com.library.reservationservice.dto.ReservationRequest;
import com.library.reservationservice.model.Reservation;
import com.library.reservationservice.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return service.createReservation(request);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return service.getReservationById(id);
    }

    @PutMapping("/{id}/cancel")
    public Reservation cancelReservation(@PathVariable Long id) {
        return service.cancelReservation(id);
    }
}