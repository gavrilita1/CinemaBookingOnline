package com.example.cinemaBookingOnline.model.dto;

import java.util.Set;

public record BookingRequestDto(
        Long screeningId, Set<Long> seatIds
) {
}
