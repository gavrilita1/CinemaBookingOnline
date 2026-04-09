package com.example.cinemaBookingOnline.service.impl;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.dto.ScreeningResponseDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import com.example.cinemaBookingOnline.model.entities.Movie;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.repository.CinemaRoomRepository;
import com.example.cinemaBookingOnline.repository.MovieRepository;
import com.example.cinemaBookingOnline.repository.ScreeningRepository;
import com.example.cinemaBookingOnline.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {


    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final CinemaRoomRepository cinemaRoomRepository;


    @Override
    public ScreeningResponseDto createScreening(ScreeningRequestDTO dto) {

        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new RuntimeException("Movie not found : " + dto.movieId()));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(dto.roomId())
                .orElseThrow(() -> new RuntimeException("Room not found " + dto.roomId()));

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setPrice(dto.price());
        screening.setStartTime(LocalDate.now());

        return toDto(screeningRepository.save(screening));
    }

    @Override
    public List<ScreeningResponseDto> getAllScreenings() {
        return screeningRepository.findAll()
                .stream()
                .map( screening -> toDto(screening))
                .toList();
    }

    @Override
    public ScreeningResponseDto getScreeningById(Long id) {
        return toDto(screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screen not found")));
    }

    @Override
    public List<ScreeningResponseDto> getScreeningsByMovieId(Long id) {
        return screeningRepository.findByMovieId(id)
                .stream()
                .map( screening -> toDto(screening) )
                .toList();
    }

    @Override
    public List<ScreeningResponseDto> getScreeningsByRoomId(Long id) {
        return screeningRepository.findByCinemaRoomId(id)
                .stream()
                .map(screening -> toDto(screening))
                .toList();
    }

    @Override
    public List<ScreeningResponseDto> getScrenningsByPeriod(LocalDate start, LocalDate end) {
        return screeningRepository.findByStartTimeBetween(start, end)
                .stream()
                .map(screening -> toDto(screening))
                .toList();
    }

    @Override
    public List<ScreeningResponseDto> getScrenningsByPeriodQuery(LocalDate start, LocalDate end) {
        return screeningRepository.getScreeningsByPeriod(start, end)
                .stream()
                .map(screening -> toDto(screening))
                .toList();
    }

    @Override
    public ScreeningResponseDto updateScreening(Long id, ScreeningRequestDTO dto) {

        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screen not found"));
        screening.setMovie(movieRepository.findById(dto.movieId()).orElseThrow(() -> new RuntimeException()));
        screening.setCinemaRoom(cinemaRoomRepository.findById(dto.roomId()).orElseThrow(() -> new RuntimeException()));
        screening.setPrice(dto.price());
        return toDto( screeningRepository.save(screening));
    }

    @Override
    public void deleteScreening(Long id) {
        screeningRepository.deleteById(id);
    }
}
