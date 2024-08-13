package com.revature.DAOs;

import com.revature.Models.Rental;

import java.util.ArrayList;

public interface RentalDAOInterface {

    ArrayList<Rental> getRentals();

    Rental getRentalByUserID(int id);

    Rental insertRental(Rental rental);

    Rental deleteRental(int rentalID);
}
