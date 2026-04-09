package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.dto.ScreeningResponseDto;
import com.example.cinemaBookingOnline.model.entities.Screening;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningService {

    public ScreeningResponseDto createScreening(ScreeningRequestDTO dto);
    public List<ScreeningResponseDto> getAllScreenings();
    public ScreeningResponseDto getScreeningById(Long id);
    public List<ScreeningResponseDto> getScreeningsByMovieId(Long id);
    public List<ScreeningResponseDto> getScreeningsByRoomId(Long id);
    public List<ScreeningResponseDto> getScrenningsByPeriod(LocalDate start, LocalDate end);
    public List<ScreeningResponseDto> getScrenningsByPeriodQuery(LocalDate start, LocalDate end);
    public ScreeningResponseDto updateScreening(Long id, ScreeningRequestDTO screening);
    public void deleteScreening(Long id);

    public default ScreeningResponseDto toDto(Screening screening){
        return new ScreeningResponseDto(
                screening.getId(),
                screening.getMovie().getTitle(),
                screening.getCinemaRoom().getName(),
                screening.getPrice(),
                screening.getStartTime()
                );
    }
}
