package com.revature.DAOs;

import com.revature.Models.Movie;
import com.revature.Utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO implements MovieDAOInterface {


    @Override
    public ArrayList<Movie> getMovies() {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from movies order by movie_id asc";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Movie> movies = new ArrayList<>();

            while(rs.next()) {
                Movie m = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("rating"),
                        rs.getInt("copies_available")
                );
                movies.add(m);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Movie getMovieByID(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from movies where movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("rating"),
                        rs.getInt("copies_available")
                );
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not find movie by ID.");
        }

        return null;
    }


    @Override
    public Movie insertMovie(Movie mov) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into movies (title, genre, rating, copies_available)" +
                         "values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mov.getTitle());
            ps.setString(2, mov.getGenre());
            ps.setString(3, mov.getRating());
            ps.setInt(4, mov.getCopies_available());
            ps.executeUpdate();
            return mov;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert movie.");
        }

        return null;
    }

    @Override
    public Movie updateAvailableCopies(int movie_id, int copies_available) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "update movies set copies_available = ? where movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, copies_available);
            ps.setInt(2, movie_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
