package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {
}
