package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Seat;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByCinemaRoomId(Long id);
}
