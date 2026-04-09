package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.CinemaRoomRequestDto;
import com.example.cinemaBookingOnline.model.dto.CinemaRoomResponseDto;
import com.example.cinemaBookingOnline.service.CinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cinemarooms")
@RequiredArgsConstructor
public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;

    @PostMapping
    public CinemaRoomResponseDto createRoom(@RequestBody CinemaRoomRequestDto dto){
        return cinemaRoomService.createRoom(dto);
    }

    @GetMapping
    public List<CinemaRoomResponseDto> getAllRooms(){
        return cinemaRoomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public CinemaRoomResponseDto getRoomById(@PathVariable Long id){
        return cinemaRoomService.getRoomById(id);
    }
}
