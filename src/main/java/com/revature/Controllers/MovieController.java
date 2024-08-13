package com.revature.Controllers;

import com.revature.Models.Movie;
import com.revature.Services.MovieService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class MovieController {

    MovieService ms = new MovieService();

    public Handler getMoviesHandler = ctx -> {
        ArrayList<Movie> movies = ms.getMovies();
        ctx.json(movies);
        ctx.status(200);
    };

    public Handler insertMovieHandler = ctx -> {
        Movie newMovie = ctx.bodyAsClass(Movie.class);
        try {
            Movie insertedMovie = ms.insertMovie(newMovie);
            ctx.status(201);
            ctx.json(insertedMovie);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown");
        }
    };

    public Handler updateAvailableCopiesHandler = ctx -> {
        Movie updateMovie = ctx.bodyAsClass(Movie.class);

        try {
            ms.updateAvailableCopies(updateMovie.getMovie_id(), updateMovie.getCopies_available());
            ctx.status(204).result("Updated Available Copies");
            ctx.json(updateMovie);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Server error " + e.getMessage());
        }
    };
}
