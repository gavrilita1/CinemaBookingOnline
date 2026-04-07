package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Screening;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    public List<Screening> findByMovieId(Long id);
    public List<Screening> findByCinemaRoomId(Long id);

    public List<Screening> findByStartTimeBetween(LocalDate start, LocalDate end);

    @Query("SELECT s FROM Screening s WHERE s.startTime BETWEEN :start AND :end")
    public List<Screening> getScreeningsByPeriod(LocalDate start, LocalDate end);

}
