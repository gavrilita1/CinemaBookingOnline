package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.SeatResponseDto;
import com.example.cinemaBookingOnline.model.entities.Seat;

import java.util.List;

public interface SeatService {
    public List<SeatResponseDto> getAllSeatsByRoomId(Long id);
    public SeatResponseDto getSeatById(Long id);

    public default SeatResponseDto toDto(Seat seat){
        return new SeatResponseDto(
                seat.getId(),
                seat.getCinemaRoom().getName(),
                "position: " + seat.getRow_number() + " / " + seat.getCol_number()
        );
    }
}
