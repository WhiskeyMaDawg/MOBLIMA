package model;

import model.enums.ShowStatus;

import model.enums.MoviesType;

/**
 * Initialiser class for 2D movie
 */
public class TwoDMovie extends Movie {

    /**
     * the base price for a 2D movie
     */
    private double price;

    /**
     * Constructor of 2D movie
     * 
     * @param movieId of the movie
     * @param title   of the movie
     * @param status  of the movie
     * @param synopsis of the movie
     * @param director of the movie
     * @param cast     of the movie
     * @param type     of the movie
     * @param price    of the movie
     */
    public TwoDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            MoviesType type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        setPrice(price);
    }
    
    /**
     * Gets the price of the movie and @return price of movie
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets base price for the movie with @param price of movie
     */
    public void setPrice(double price) {
        this.price = price;
    }    
}
