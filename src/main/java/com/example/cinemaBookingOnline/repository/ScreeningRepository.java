package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
