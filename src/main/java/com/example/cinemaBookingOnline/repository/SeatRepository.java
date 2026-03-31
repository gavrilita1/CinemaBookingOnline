package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
