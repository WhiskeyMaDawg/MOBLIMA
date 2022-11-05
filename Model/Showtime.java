package src.model;

import java.io.Serializable;

import src.model.enums.LayoutType;
import src.model.enums.SeatType;

/**
 * Showtime class for movie
 */
public class Showtime implements Serializable {
  /**
   * For Java Serializable
   */
  private static final long serialVersionUID = 5L;

  /**
   * Showtime ID
   */
  private String showtimeId;

  /**
   * ShowTIME
   */
  private String time;
  
  /**
   * Layout type for the movie
   */
  private LayoutType layoutType;

  /**
   * Movie to be shown at showtime
   */
  private Movie movie;

  /**
   * Cinema hall where movie is screened
   */
  private Cinema cinema;


  /**
   * Matrix for showtime given layout Type
   */
  private Seat[][] seats;

  /**
   * Number of COLUMNS given layoutType
   */
  private static final int COLS = 20;

  /**
   * Number of ROWS given layoutType
   */
  private static int ROWS;

  /**
   * Showtime constructor with @param time @param showtimeId @param movie @param cinema @param layoutType
   */
  public Showtime(String showtimeId, String time, Movie movie, Cinema cinema, LayoutType layoutType) {
    setShowtimeId(showtimeId);
    setTime(time);
    setMovie(movie);
    setCinema(cinema);
    setLayoutType(layoutType);
    initSeats();
  }

  /**
   * Sets the time of showtime with @param time
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Sets the showtime ID with @param showtimeId
   */
  public void setShowtimeId(String showtimeId) {
    this.showtimeId = showtimeId;
  }

  /**
   * Sets the designated movie with @param movie {@link Movie}
   */
  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  /**
   * Sets the cinema at showtime using @param cinema {@link Cinema}
   */
  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  /**
   * Sets the layout type for the cinema at given showtime using @param layoutType {@link LayoutType}
   */
  public void setLayoutType(LayoutType layoutType) {
    this.layoutType = layoutType;
  }

  /**
   * Gets and @return time of showtime
   */
  public String getTime() {
    return time;
  }

  /**
   * Gets and @return ID of the showtime
   */
  public String getShowtimeId() {
    return showtimeId;
  }

  /**
   * Gets and @return the corresponding movie
   */
  public Movie getMovie() {
    return movie;
  }

  /**
   * Gets and @return the corresponding cinema
   */
  public Cinema getCinema() {
    return this.cinema;
  }

  /**
   * Gets and @return the corresponding matrix of seats
   */
  public Seat[][] getSeats() {
    return seats;
  }

  /**
   * Gets the seat at a specified postion with @param row @param col and @return corresponding seat
   */
  public Seat getSeatAt(int row, int col) {
    return seats[row - 1][col - 1];
  }

  /**
   * Initialise the seats of the showtime with @param layoutType {@link LayoutType}
   */
  private void initSeats() {
    switch (layoutType) {
      case LARGE:
        ROWS = 12;
        break;
      case MEDIUM:
        ROWS = 10;
        break;
      case SMALL:
        ROWS = 8;
        break;
      default:
        ROWS = 10;
        break;
    }

    seats = new Seat[ROWS][COLS];

    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (row == 6)
          continue;
        if (col == 5 || col == 15)
          continue;

        if(row == ROWS-1){
          if(col < 5){
            seats[row][col] = new Seat(row, col, this, SeatType.COUPLE);
          }else if(col < 14){
            seats[row][col] = new Seat(row, col, this, SeatType.ELITE);
          }else{
            seats[row][col] = new Seat(row, col, this, SeatType.ULTIMA);
          }
        }else{
          seats[row][col] = new Seat(row, col, this, SeatType.NORMAL);
        }
      }
    }
  }
}
