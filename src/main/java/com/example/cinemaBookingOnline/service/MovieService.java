package com.example.cinemaBookingOnline.service;


import com.example.cinemaBookingOnline.model.entities.Movie;

import java.util.List;

public interface MovieService {

    public Movie createMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Movie getMovieById(Long id);
    public Movie updateMovie(Long id, Movie movie);
    public void deleteMovie(Long id);

}
