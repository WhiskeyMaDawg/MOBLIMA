package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;


public class BlockbusterMovie extends Movie { //class to initialise blockbuster movie
    
    private double price; //base price of blockbuster movie

    /**
     * Constructor BlockbusterMovie
     * 
     * @param movieId  of the movie
     * @param title    of the movie
     * @param status   of the movie
     * @param synopsis of the movie
     * @param director of the movie
     * @param cast     of the movie
     * @param type     of the movie
     * @param price    of the movie
     */
    public BlockbusterMovie(String movieId, String title, ShowStatus status, String synopsis, String director,
            String[] cast, MoviesType type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        this.price = price;
    }

    public void setPrice(double price) { //set base price of blockbuster movie
        this.price = price;
    }

    public double getPrice() { //get base price of blockbuster movie
        return price;
    }
}
