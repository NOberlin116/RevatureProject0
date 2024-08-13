package com.revature.Services;

import com.revature.DAOs.MovieDAO;
import com.revature.Models.Movie;

import java.util.ArrayList;

public class MovieService {

    MovieDAO mDAO = new MovieDAO();

    public ArrayList<Movie> getMovies() { return mDAO.getMovies(); }

    public Movie insertMovie(Movie movie) throws IllegalArgumentException {

        if(movie.getTitle() == null || movie.getTitle().equals("")) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        if(movie.getGenre() == null || movie.getGenre().equals("")) {
            throw new IllegalArgumentException("Genre must not be empty.");
        }
        if(movie.getRating() == null || movie.getRating().equals("")) {
            throw new IllegalArgumentException("Rating must not be empty.");
        }
        if(movie.getCopies_available() == 0 || movie.getTitle().equals("")) {
            throw new IllegalArgumentException("Copies Available must be >= 1");
        }
        //TODO check if movie already exists, if yes add 1 to copies available
        mDAO.insertMovie(movie);
        return movie;
    }

    public void updateAvailableCopies(int movie_id, int copies_available) throws IllegalArgumentException {
        if (movie_id <= 0) {
            throw new IllegalArgumentException("User ID must be 1 or higher.");
        }
        if (copies_available <= 0) {
            throw new IllegalArgumentException("Available Copies must be 1 or higher.");
        }
        mDAO.updateAvailableCopies(movie_id, copies_available);
    }
}
