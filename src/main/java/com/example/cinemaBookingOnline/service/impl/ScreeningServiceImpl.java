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

import java.time.LocalDate;
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
                .orElseThrow(() -> new RuntimeException("Movie not found : " + dto.movieId()));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(dto.roomId())
                .orElseThrow(() -> new RuntimeException("Room not found " + dto.roomId()));

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setPrice(dto.price());
        screening.setStartTime(LocalDate.now());


        return screeningRepository.save(screening);
    }

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screen not found"));
    }

    @Override
    public List<Screening> getScreeningsByMovieId(Long id) {
        return screeningRepository.findByMovieId(id);
    }

    @Override
    public List<Screening> getScreeningsByRoomId(Long id) {
        return screeningRepository.findByRoomId(id);
    }

    @Override
    public List<Screening> getScrenningsByPeriod(LocalDate start, LocalDate end) {
        return screeningRepository.findByStartTimeBetween(start, end);
    }

    @Override
    public List<Screening> getScrenningsByPeriodQuery(LocalDate start, LocalDate end) {
        return screeningRepository.getScreeningsByPeriod(start, end);
    }

    @Override
    public Screening updateScreening(Long id, ScreeningRequestDTO dto) {
        Screening screening = getScreeningById(id);
        screening.setMovie(movieRepository.findById(dto.movieId()).orElseThrow(() -> new RuntimeException()));
        screening.setCinemaRoom(cinemaRoomRepository.findById(dto.roomId()).orElseThrow(() -> new RuntimeException()));
        screening.setPrice(dto.price());
        return screeningRepository.save(screening);
    }

    @Override
    public void deleteScreening(Long id) {
        screeningRepository.deleteById(id);
    }
}
