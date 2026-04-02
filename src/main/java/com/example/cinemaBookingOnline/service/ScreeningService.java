package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.entities.Screening;

import java.util.List;

public interface ScreeningService {

    public Screening createScreening(ScreeningRequestDTO dto);
    public List<Screening> getAllScreenings();
    public Screening getScreeningById(Long id);
    public List<Screening> getScreeningsByMovieId(Long id);
    public List<Screening> getScreeningsByRoomId(Long id);
    public List<Screening> getCurrentScrennings();
    public Screening updateScreening(Long id, Screening screening);
    public void deleteScreening(Long id);

}
