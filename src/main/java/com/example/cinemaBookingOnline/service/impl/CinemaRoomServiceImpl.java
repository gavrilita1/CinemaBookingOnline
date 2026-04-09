package com.example.cinemaBookingOnline.service.impl;

import com.example.cinemaBookingOnline.model.dto.CinemaRoomRequestDto;
import com.example.cinemaBookingOnline.model.dto.CinemaRoomResponseDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import com.example.cinemaBookingOnline.model.entities.Seat;
import com.example.cinemaBookingOnline.repository.CinemaRoomRepository;
import com.example.cinemaBookingOnline.repository.SeatRepository;
import com.example.cinemaBookingOnline.service.CinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaRoomServiceImpl implements CinemaRoomService {

    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeatRepository seatRepository;

    @Override
    public CinemaRoomResponseDto createRoom(CinemaRoomRequestDto dto) {
        CinemaRoom room = new CinemaRoom();
        room.setCols_count(dto.cols_count());
        room.setRows_count(dto.rows_count());
        room.setName(dto.name());
        CinemaRoom roomSaved = cinemaRoomRepository.save(room);

        for(int row = 1; row <= roomSaved.getRows_count(); row ++){
            for (int col = 1; col <= roomSaved.getCols_count(); col++){
                Seat seat = new Seat();
                seat.setRow_number(row);
                seat.setCol_number(col);
                seat.setCinemaRoom(roomSaved);
                seatRepository.save(seat);
            }
        }

        return toDto(roomSaved);
    }

    @Override
    public List<CinemaRoomResponseDto> getAllRooms() {
        return cinemaRoomRepository.findAll()
                .stream()
                .map(cinemaRoom -> toDto(cinemaRoom))
                .toList();
    }

    @Override
    public CinemaRoomResponseDto getRoomById(Long id) {
        return toDto(cinemaRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found")));
    }
}
