package src.model;

import java.io.Serializable;

/**
 * Review class
 */

public class Review implements Serializable {
    /*
     * For Java Serializable
     */
    private static final long serialVersionUID = 7L;

    /**
     * Content of review
     */
    private String review;

    /**
     * Rating of review 
     */
    private double rating;

    /**
     * Review constructor with @param review @param rating
     */
    public Review(String review, double rating) {
        setReview(review);
        setRating(rating);
    }

    /**
     * Sets the content of review using @param review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Sets the rating of review using @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets and @return content of review
     */
    public String getReview() {
        return review;
    }

    /**
     * Gets and @return rating of review
     */
    public double getRating() {
        return rating;
    }
}
