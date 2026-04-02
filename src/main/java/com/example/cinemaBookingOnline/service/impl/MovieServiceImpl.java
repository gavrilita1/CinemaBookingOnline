package com.example.cinemaBookingOnline.service.impl;

import com.example.cinemaBookingOnline.model.entities.Movie;
import com.example.cinemaBookingOnline.repository.MovieRepository;
import com.example.cinemaBookingOnline.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found with id: " + id));
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie movieFound = movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found: " + id));
        movieFound.setTitle(movie.getTitle());
        movieFound.setRating(movie.getRating());
        movieFound.setReleaseYear(movie.getReleaseYear());
        return movieRepository.save(movieFound);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
