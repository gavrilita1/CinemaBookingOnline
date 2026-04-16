package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.BookingRequestDto;
import com.example.cinemaBookingOnline.model.dto.BookingResponseDto;
import com.example.cinemaBookingOnline.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDto createBooking(@RequestBody BookingRequestDto dto){
        return bookingService.createBooking(dto);
    }

    @PutMapping("/confirmations/{id}")
    public BookingResponseDto confirmBooking(@PathVariable Long id){
        return bookingService.confirmBooking(id);
    }

    @PutMapping("/cancellations/{id}")
    public void cancelBooking(@PathVariable Long id){
        bookingService.cancelBooking(id);
    }

    @GetMapping("{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }
}
