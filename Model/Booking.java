package src.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable { //class to store a booking
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 9L;
    private String transactionId; //transaction id for booking
    private Ticket ticket; //ticket for booking
    private MovieGoer movieGoer; //moviegoer for booking
    private ArrayList<Seat> seat; //list of booked seats for booking

    /**
     * Constructor of Booking
     *
     * @param transactionId of the booking
     * @param ticket        of the booking
     * @param movieGoer     of the booking
     * @param seat of the booking
     */
    public Booking(String transactionId, Ticket ticket, MovieGoer movieGoer, ArrayList<Seat> seat) {
        this.transactionId = transactionId;
        this.ticket = ticket;
        this.movieGoer = movieGoer;
        this.seat = seat;
    }

    public void setTransactionId(String transactionId) { //set transaction id for booking
        this.transactionId = transactionId;
    }

    public void setTicket(Ticket ticket) { //set ticket for booking
        this.ticket = ticket;
    }

    public void setMovieGoer(MovieGoer movieGoer) { //set MovieGoer for booking
        this.movieGoer = movieGoer;
    }

    public void setSeat(ArrayList<Seat> seat){ //set seat for booking
        this.seat = seat;
    }

    public String getTransactionId() { //get transaction id for booking
        return this.transactionId;
    }

    public Ticket getTicket() { //get ticket for booking
        return this.ticket;
    }

    public MovieGoer getMovieGoer() { //get MovieGoer for booking
        return this.movieGoer;
    }

    public ArrayList<Seat> getSeat(){ //get seat for booking
        return this.seat;
    }
    
}
