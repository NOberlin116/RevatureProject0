package com.revature.Models;

public class Movie {

    private int movie_id;
    private String title;
    private String genre;
    private String rating;
    private int copies_available;

    public Movie() {
    }

    public Movie(int movie_id, String title, String genre, String rating, int copies_available) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.copies_available = copies_available;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getCopies_available() {
        return copies_available;
    }

    public void setCopies_available(int copies_available) {
        this.copies_available = copies_available;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", copies_available=" + copies_available +
                '}';
    }
}
