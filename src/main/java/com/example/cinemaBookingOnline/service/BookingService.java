package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.BookingRequestDto;
import com.example.cinemaBookingOnline.model.dto.BookingResponseDto;
import com.example.cinemaBookingOnline.model.entities.Booking;

import java.util.stream.Collectors;

public interface BookingService {
    public BookingResponseDto createBooking(BookingRequestDto dto);
    public BookingResponseDto confirmBooking(Long id);
    public void cancelBooking(Long id);
    public BookingResponseDto getBookingById(Long id);

    public default BookingResponseDto toDto(Booking booking){
        return new BookingResponseDto(
          booking.getId(),
          booking.getScreening().getMovie().getTitle(),
          booking.getScreening().getMovie().getId(),
          booking.getScreening().getCinemaRoom().getName(),
          booking.getSeats()
                  .stream()
                  .map(seat -> seat.getId())
                  .collect(Collectors.toSet()),
          booking.getCreatedAt(),
          booking.getStatus()
        );
    }

}
