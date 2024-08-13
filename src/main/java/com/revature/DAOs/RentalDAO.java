package com.revature.DAOs;

import com.revature.Models.Movie;
import com.revature.Models.Rental;
import com.revature.Models.User;
import com.revature.Utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class RentalDAO implements RentalDAOInterface{


    @Override
    public ArrayList<Rental> getRentals() {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from rentals order by rental_id asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Rental> r = new ArrayList<>();

            while(rs.next()) {
                Rental rental = new Rental(
                        rs.getInt("rental_id"),
                        null,
                        0,
                        null,
                        0,
                        rs.getString("rental_date")

                );
                MovieDAO mDAO = new MovieDAO();
                Movie movie = mDAO.getMovieByID(rs.getInt("movie_id_fk"));
                rental.setMovie(movie);
                rental.setMovie_id_fk(movie.getMovie_id());

                UserDAO uDAO = new UserDAO();
                User user = uDAO.getUserByID(rs.getInt("user_id_fk"));
                rental.setUser(user);
                rental.setUser_id_fk(user.getUser_id());

                r.add(rental);

            }
        return r;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Rental getRentalByUserID(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from rentals where user_id_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Rental rental = new Rental(
                        rs.getInt("rental_id"),
                        null,
                        0,
                        null,
                        0,
                        rs.getString("rental_date")

                );
                MovieDAO mDAO = new MovieDAO();
                Movie movie = mDAO.getMovieByID(rs.getInt("movie_id_fk"));
                rental.setMovie(movie);
                rental.setMovie_id_fk(movie.getMovie_id());

                UserDAO uDAO = new UserDAO();
                User user = uDAO.getUserByID(rs.getInt("user_id_fk"));
                rental.setUser(user);
                rental.setUser_id_fk(user.getUser_id());

                return rental;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Rental insertRental(Rental rental) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into rentals (user_id_fk, movie_id_fk, rental_date) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rental.getUser_id_fk());
            ps.setInt(2, rental.getMovie_id_fk());
            ps.setString(3, rental.getRental_date());
            MovieDAO mDAO = new MovieDAO();
            Movie movie = mDAO.getMovieByID(rental.getMovie_id_fk());
            rental.setMovie(movie);
            UserDAO uDAO = new UserDAO();
            User user = uDAO.getUserByID(rental.getUser_id_fk());
            rental.setUser(user);
            ps.executeUpdate();
            return rental;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert new User.");
        }

        return null;
    }

    @Override
    public Rental deleteRental(int rentalID) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "delete from rentals where rental_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rentalID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
