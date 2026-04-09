package com.example.cinemaBookingOnline.service;

import com.example.cinemaBookingOnline.model.dto.CinemaRoomRequestDto;
import com.example.cinemaBookingOnline.model.dto.CinemaRoomResponseDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;

import java.util.List;

public interface CinemaRoomService {
    public CinemaRoomResponseDto createRoom(CinemaRoomRequestDto dto);
    public List<CinemaRoomResponseDto> getAllRooms();
    public CinemaRoomResponseDto getRoomById(Long id);

    public default CinemaRoomResponseDto toDto(CinemaRoom cinemaRoom){
        return new CinemaRoomResponseDto(
                cinemaRoom.getId(),
                cinemaRoom.getCols_count() + cinemaRoom.getRows_count(),
                cinemaRoom.getName()
        );
    }
}
