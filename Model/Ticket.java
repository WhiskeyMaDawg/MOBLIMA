package src.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Movie ticket Class
 */

public class Ticket implements Serializable {
    /**
     * For java serializable
     */
    protected static final long serialVersionUID = 9L;

    /**
     * Ticket Showtime
     */
    private Showtime showtime;

    /**
     * Ticket Price
     */ 
    private double price;

    /**
     * List of {@link Seat} for tickets
     */
    private ArrayList<Seat> seat;

    /**
     * Status of the ticket (paid/ready for payment)
     */
    private boolean isPaid;


    /**
     * Ticket constructor with @param showtime @param price @param seat of ticket
     */
    public Ticket(Showtime showtime,double price, ArrayList<Seat> seat) {
        setShowtime(showtime);
        setPrice(price);
        setSeat(seat);
        setIsPaid(false);
    }

    /**
     * Gets and @return {@link Showtime} of the ticket
     */
    public Showtime getShowtime(){
        return this.showtime;
    }

    /**
     * Sets the showtime of ticket with @param showtime
     */
    public void setShowtime(Showtime showtime){
        this.showtime = showtime;
    }

    /**
     * Sets the {@link Seat} of ticket with @param seat
     */
    public void setSeat(ArrayList<Seat> seat) {
        this.seat = seat;
    }

    /**
     * Sets the status of the ticket with @param status
     */
    public void setIsPaid(boolean status) {
        this.isPaid = status;
    }

    /**
     * Gets and @return price of the ticket
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the ticket with @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets and @return list of seat of ticket
     */
    public ArrayList<Seat> getSeat() {
        return seat;
    }

    /**
     * Get and @return boolean {@code true} if ticket was paid, {@code false} otherwise
     */
    public boolean getIsPaid() {
        return isPaid;
    }


}
