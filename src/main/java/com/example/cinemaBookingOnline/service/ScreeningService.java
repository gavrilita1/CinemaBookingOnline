package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.entities.Screening;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningService {

    public Screening createScreening(ScreeningRequestDTO dto);
    public List<Screening> getAllScreenings();
    public Screening getScreeningById(Long id);
    public List<Screening> getScreeningsByMovieId(Long id);
    public List<Screening> getScreeningsByRoomId(Long id);
    public List<Screening> getScrenningsByPeriod(LocalDate start, LocalDate end);
    public List<Screening> getScrenningsByPeriodQuery(LocalDate start, LocalDate end);
    public Screening updateScreening(Long id, ScreeningRequestDTO screening);
    public void deleteScreening(Long id);

}
