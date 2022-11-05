package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 * Initialiser class for 2D movie
 */
public class ThreeDMovie extends Movie {
    /**
     * the base price of a 3D movie
     */
    private double price;

    /**
     * Constructor of 3D movie
     * 
     * @param movieId of movie
     * @param title of movie
     * @param status of movie
     * @param synopsis of movie
     * @param director of movie
     * @param cast of movie
     * @param type of movie
     * @param price of 3D movie
     */
    public ThreeDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
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
