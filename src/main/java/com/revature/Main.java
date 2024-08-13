package com.revature;

import com.revature.Controllers.MovieController;
import com.revature.Controllers.RentalController;
import com.revature.Controllers.UserController;

import com.revature.Utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = ConnectionUtil.getConnection()){
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }


        var app = Javalin.create().start(3000).get("/", ctx -> ctx.result("Hello Postman."));

        MovieController mc = new MovieController();
        UserController uc = new UserController();
        RentalController rc = new RentalController();

        app.get("/movies", mc.getMoviesHandler);
        app.post("/movies", mc.insertMovieHandler);
        app.put("/movies/copies_available", mc.updateAvailableCopiesHandler);

        app.get("/users", uc.getUsersHandler);
        app.post("/users", uc.insertUserHandler);
        app.put("/users/email", uc.updateUserEmailHandler);

        app.get("/rentals", rc.getRentalsHandler);
        app.get("/rentals/{id}", rc.getRentalsByUserIDHandler);
        app.post("/rentals", rc.insertRentalHandler);
        app.delete("/rentals/{id}", rc.deleteRentalHandler);

    }

}
