package com.example.cinemaBookingOnline.service;


import com.example.cinemaBookingOnline.model.dto.MovieRequestDto;
import com.example.cinemaBookingOnline.model.dto.MovieResponseDto;
import com.example.cinemaBookingOnline.model.entities.Movie;

import java.util.List;

public interface MovieService {

    public MovieResponseDto createMovie(MovieRequestDto dto);
    public List<MovieResponseDto> getAllMovies();
    public MovieResponseDto getMovieById(Long id);
    public MovieResponseDto updateMovie(Long id, MovieRequestDto movie);
    public void deleteMovie(Long id);

    public default MovieResponseDto toDto(Movie movie){
        MovieResponseDto dto = new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getRating(),
                movie.getReleaseYear()
        );
        return dto;
    }
}
