package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.CinemaRoomRequestDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;

import java.util.List;

public interface CinemaRoomService {
    public CinemaRoom createRoom(CinemaRoomRequestDto dto);
    public List<CinemaRoom> getAllRooms();
    public CinemaRoom getRoomById(Long id);
}
