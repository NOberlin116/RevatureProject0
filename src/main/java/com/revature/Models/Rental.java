package com.revature.Models;

public class Rental {
    private int rental_id;
    private Movie movie;
    private int movie_id_fk;
    private User user;
    private int user_id_fk;
    private String rental_date;

    public Rental() {
    }

    public Rental(int rental_id, Movie movie, int movie_id_fk, User user, int user_id_fk, String rental_date) {
        this.rental_id = rental_id;
        this.movie = movie;
        this.movie_id_fk = movie_id_fk;
        this.user = user;
        this.user_id_fk = user_id_fk;
        this.rental_date = rental_date;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getMovie_id_fk() {
        return movie_id_fk;
    }

    public void setMovie_id_fk(int movie_id_fk) {
        this.movie_id_fk = movie_id_fk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(int user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public String getRental_date() {
        return rental_date;
    }

    public void setReturn_date(String rental_date) {
        this.rental_date = rental_date;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rental_date='" + rental_date + '\'' +
                ", user=" + user +
                ", movie=" + movie +
                ", rental_id=" + rental_id +
                '}';
    }
}
