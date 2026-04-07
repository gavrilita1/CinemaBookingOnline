package com.example.cinemaBookingOnline.model.dto;

public record MovieResponseDto(
        Long id, String title, Double rating, int releaseYear
) {
}
