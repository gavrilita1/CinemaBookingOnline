package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.CinemaRoomRequestDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
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
    public CinemaRoom createRoom(@RequestBody CinemaRoomRequestDto dto){
        return cinemaRoomService.createRoom(dto);
    }

    @GetMapping
    public List<CinemaRoom> getAllRooms(){
        return cinemaRoomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public CinemaRoom getRoomById(@PathVariable Long id){
        return cinemaRoomService.getRoomById(id);
    }
}
