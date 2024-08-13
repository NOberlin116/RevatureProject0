package com.revature.DAOs;

import com.revature.Models.Movie;

import java.util.ArrayList;

public interface MovieDAOInterface {

    ArrayList<Movie> getMovies();

    Movie getMovieByID(int id);

    Movie insertMovie(Movie mov);

    Movie updateAvailableCopies(int movie_id, int copies_available);

}
