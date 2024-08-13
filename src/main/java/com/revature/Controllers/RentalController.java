package com.revature.Controllers;

import com.revature.DAOs.RentalDAO;
import com.revature.Models.Movie;
import com.revature.Models.Rental;
import com.revature.Models.User;
import com.revature.Services.RentalService;
import com.sun.jdi.request.ExceptionRequest;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class RentalController {

    RentalDAO rDAO = new RentalDAO();

    RentalService rs = new RentalService();

    public Handler getRentalsHandler = ctx -> {
        ArrayList<Rental> rentals = rs.getRentals();
        ctx.json(rentals);
        ctx.status(200);
    };

    public Handler getRentalsByUserIDHandler = ctx -> {

        int user_id = Integer.parseInt(ctx.pathParam("id"));
        Rental rental = rDAO.getRentalByUserID(user_id);

        if (rental == null){
            ctx.status(400);
            ctx.result("Rental not found.");
        } else {
            ctx.status(200);
            ctx.json(rental);
        }
    };

    public Handler insertRentalHandler = ctx -> {
        Rental newRental = ctx.bodyAsClass(Rental.class);
        try {
            Rental insertedRental = rs.insertRental(newRental);
            ctx.status(201);
            ctx.json(insertedRental);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown");
        }
    };

    public Handler deleteRentalHandler = ctx -> {
        int rentalID = Integer.parseInt(ctx.pathParam("id"));

        try {
            rs.deleteRental(rentalID);
            ctx.status(202).result("Rental Deleted");
        } catch (Exception e) {
            ctx.status(500).result("Server error " + e.getMessage());
        }
    };
}
