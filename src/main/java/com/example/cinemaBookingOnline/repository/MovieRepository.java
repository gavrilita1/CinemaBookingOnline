package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
