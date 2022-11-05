package src.model;

import java.io.Serializable;
import java.util.ArrayList;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 *  A movie class
 */

public abstract class Movie implements Serializable, Comparable<Movie> {
    /**
     * For java serializable
     */
    protected static final long serialVersionUID = 6L;

    /**
     * Movie ID
     */
    private String movieId;

    /**
     * Movie Title
     */
    private String title;

    /**
     * Reveals {@link ShowStatus} of movie
     */
    private ShowStatus status;

    /**
     * Movie's Synopsis
     */
    private String synopsis;

    /**
     * Movie Director
     */
    private String director;

    /**
     * Movie Cast
     */
    private String[] cast;

    /**
     * Movie Total Ticket Sales
     */
    private int ticketSales;

    /**
     * overall rating of movie based on average of ratings
     */
    private double overallRating;

    /**
     * List of {@link Review} of movie
     */
    private ArrayList<Review> reviews;

    /**
     * {@link MoviesType} of movie
     */
    private MoviesType type;

    /**
     * Constructor of Movie
     * 
     * @param movieId of movie
     * @param title of movie 
     * @param status of movie 
     * @param synopsis of movie
     * @param director  of movie
     * @param cast of movie
     * @param type of movie
     * 
     */
    public Movie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast, MoviesType type) {
        setMovieId(movieId);
        setTitle(title);
        setStatus(status);
        setSynopsis(synopsis);
        setDirector(director);
        setCast(cast);
        setType(type);
        setOverallRating(-1);
        setTicketSales(0);
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Abstract method - Gets and @return price of movie
     */
    public abstract double getPrice();

    /**
     * Abstract method - Sets the price of the movie
     * */
    public abstract void setPrice(double price);

    /**
     * Sets the ID of the movie with @param movieId
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * Sets the title of the movie with @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets {@link ShowStatus} of the movie using @param status
     */
    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    /**
     * Sets the synopsis of the movie with @param synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Sets the director of the movie with @param director
     */ 
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the cast of the movie using @param cast
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Sets the total ticket sales of the movie using @param ticketSales
     */
    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    /**
     * Sets the overall rating of the movie using @param overallRating
     */
    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * Adds a new @param review to the list of {@link Reviews} of the movie
    public void addReview(Review review) {
        reviews.add(review);
    }
        
    /**
     * Sets the {@link MoviesType} of the movie with @param type of the movie
     */
    public void setType(MoviesType type) {
        this.type = type;
    }

    /**
     * Gets and @return movie ID
     */
    public String getMovieId() {
        return movieId;
    }
    
    /**
     * Gets and @return movie title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets and @return {@link ShowStatus} of the movie
     */
    public ShowStatus getStatus() {
        return status;
    }
    /**
     * Gets and @return synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }
    /**
     * Gets and @return director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets and @return cast of the movie
     */
    public String[] getCast() {
        return cast;
    }
    /**
     * Gets and @return total ticket sales of the movie
     */
    public int getTicketSales() {
        return ticketSales;
    }
    /**
     * Gets and @return overall rating of the movie
     */
    public double getOverallRating() {
        if (reviews.size() == 0) {
            return -1;
        }

        double dummy = 0;
        for (int i = 0; i < reviews.size(); i++) {
            dummy += reviews.get(i).getRating();
        }
        dummy /= reviews.size();
        setOverallRating(dummy);
        return dummy;
    }
    
    /**
     * Gets and @return list of {@link Review} of the movie
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Gets and @return {@link MoviesType} of the movie
     */
    public MoviesType getType() {
        return type;
    }

    /**
     * Compare the overall ratings between two movies with @param movie the movie to be compared with
     * @return integer {@code 1} if the current movie's overall rating is higher, {@code -1} otherwise
     */
    @Override
    public int compareTo(Movie movie) {
        if (this == movie) {
            return 0;
        }
        double thisOverallRating = this.getOverallRating();
        double otherMovieRating = movie.getOverallRating();

        return thisOverallRating > otherMovieRating ? 1 : -1;
    }

    /**
     * Compare movies based on their ticket sales with @param movie that is to be compared with
     * @return the difference between the ticket sales of both movies
     */
    public int compareToTicketSales(Movie movie) {
        if (this == movie) {
            return 0;
        }
        int thisTicketSales = this.getTicketSales();
        int otherTicketSales = movie.getTicketSales();

        return (thisTicketSales - otherTicketSales);
    }
}
