package com.revature.Services;

import com.revature.DAOs.RentalDAO;
import com.revature.Models.Movie;
import com.revature.Models.Rental;

import java.util.ArrayList;

public class RentalService {

    RentalDAO rDAO = new RentalDAO();

    public ArrayList<Rental> getRentals() { return rDAO.getRentals(); }

    public Rental insertRental(Rental rental) throws IllegalArgumentException {

        if (rental.getUser_id_fk() <= 0) {
            throw new IllegalArgumentException("User ID must be >= 1");
        }
        if (rental.getMovie_id_fk() <= 0) {
            throw new IllegalArgumentException("Movie ID must be >= 1");
        }
        if (rental.getRental_date() == null || rental.getRental_date().equals("")) {
            throw new IllegalArgumentException("Rental Date must not be empty");
        }

        rDAO.insertRental(rental);

        return rental;
    }

    public void deleteRental(int rentalID) throws IllegalArgumentException{
        if (rentalID <= 0) {
            throw new IllegalArgumentException("Rental ID must be >= 1");
        }
        rDAO.deleteRental(rentalID);
    }

}
