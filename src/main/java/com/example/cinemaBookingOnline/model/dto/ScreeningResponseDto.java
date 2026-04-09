package com.example.cinemaBookingOnline.model.dto;

import java.time.LocalDate;

public record ScreeningResponseDto(
        Long id, String movieName, String cinemaRoom, Double price, LocalDate time
) {
}
