package src.model;

import java.io.Serializable;

import src.model.enums.SeatType;

/**
 * Class for seats
 */
public class Seat implements Serializable {
  /*
   * For Java Serializable
   */
  private static final long serialVersionUID = 6L;

  /**
   * Seat is booked
   */
  private boolean booked;

  /**
   * Row of seat
   */
  private int row;

  /**
   * Column of seat
   */
  private int col;

  /**
   * Corresponding showtime
   */
  private Showtime showtime;

  /**
   * Corresponding seatType
   */
  private SeatType seatType;

  /**
   * Corresponding position of the seat
   */
  private String position;

  /**
   * Constructor of Seat with @param row @param col @param showtime @param seatType as parameters
   */
  public Seat(int row, int col, Showtime showtime, SeatType seatType) {
    setPos(row, col);
    setShowtime(showtime);
    setBooked(false);
    setSeatType(seatType);
  }

  /**
   * Sets the position of seat with @param row @param col
   */
  public void setPos(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Sets if the seat is booked using @param booked
   */
  public void setBooked(boolean booked) {
    this.booked = booked;
  }

  /**
   * Sets the {@link Showtime} of the seat with @param showtime
   */
  public void setShowtime(Showtime showtime) {
    this.showtime = showtime;
  }

  /**
   * Sets the {@link SeatType} of the seat using @param seatType
   */
  public void setSeatType(SeatType seatType){
    this.seatType = seatType;
  }
  
  /**
   * Sets the position of the seat using @param position
   */
  public void setPosition(String position){
    this.position = position;
  }

  /**
   * Gets and @return {@code true} whether the seat is booked. Otherwise, {@code false}
   */
  public boolean getBooked() {
    return booked;
  }
  
  /**
   * Gets and @return corresponding column of seat
   */
  public int getCol(){
    return this.col;
  }
  /**
   * Gets and @return corresponding row of seat
   */
  public int getRow(){
    return this.row;
  }

  /**
   * Gets and @return the corresponding {@link Showtime} the seat belongs to
   */
  public Showtime getShowtime() {
    return showtime;
  }

  /**
   * Gets and @return the corresponding {@link SeatType} of the seat
   */
  public SeatType getSeatType(){
    return this.seatType;
  }

  /**
   * Gets and @return corresponding position of the seat
   */
  public String getPosition(){
    return this.position;
  }

}
