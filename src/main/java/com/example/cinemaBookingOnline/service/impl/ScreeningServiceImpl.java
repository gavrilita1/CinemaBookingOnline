package com.example.cinemaBookingOnline.service.impl;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import com.example.cinemaBookingOnline.model.entities.Movie;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.repository.CinemaRoomRepository;
import com.example.cinemaBookingOnline.repository.MovieRepository;
import com.example.cinemaBookingOnline.repository.ScreeningRepository;
import com.example.cinemaBookingOnline.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {


    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final CinemaRoomRepository cinemaRoomRepository;


    @Override
    public Screening createScreening(ScreeningRequestDTO dto) {

        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(()-> new RuntimeException("Movie not found : " + dto.movieId()));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(dto.roomId())
                .orElseThrow(()-> new RuntimeException("Room not found " + dto.roomId()));


        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setPrice(dto.price());
        screening.setStartTime(LocalDateTime.now());


        return screeningRepository.save(screening);
    }

    @Override
    public List<Screening> getAllScreenings() {
        return List.of();
    }

    @Override
    public Screening getScreeningById(Long id) {
        return null;
    }

    @Override
    public List<Screening> getScreeningsByMovieId(Long id) {
        return List.of();
    }

    @Override
    public List<Screening> getScreeningsByRoomId(Long id) {
        return List.of();
    }

    @Override
    public List<Screening> getCurrentScrennings() {
        return List.of();
    }

    @Override
    public Screening updateScreening(Long id, Screening screening) {
        return null;
    }

    @Override
    public void deleteScreening(Long id) {

    }
}
