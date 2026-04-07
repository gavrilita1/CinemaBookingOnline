package com.example.cinemaBookingOnline.model.dto;

public record MovieRequestDto(
        String title, Double rating, int releaseYear
) {
}
