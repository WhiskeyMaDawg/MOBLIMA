package MobView;
import database.Database;
import Helper.Helper;
import controller.*;

public class DatabaseView implements ViewInterface {
  /**
   * Default constructor of DatabaseView.
   */

  /**
   * View Menu of the DatabaseView.
   */
  
  public void printOptions() {
	printPath p = new printPath();
    Helper.clearScreen();
    p.printRoute("Cineplex App > Staff > Database");
    System.out.println("What would you like to do ?");
    System.out.println("Option 1: Add Cineplex");
    System.out.println("Option 2: Add Movies");
    System.out.println("Option 3: Add Showtimes");
    System.out.println("Option 4: Add Holidays");
    System.out.println("Option 5: Change Ticket Prices");
    System.out.println("Option 6: Reset Database");
    System.out.println("Option 7: Exit");
  }

  /**
   * View application of the DatabaseView.
   * <p>
   * See {@link Database} for more details.
   */
  @Override
  public void applicationView() {
	printOptions();
	int option = Helper.readInt(1, 7);
    System.out.println();
    
    printPath p = new printPath();
    
    while(option!=7) {
     
      switch (option) {
        case 1:
          p.printRoute("Cineplex App > Staff > Database > Initialise Cineplex");
          if (initializeCineplex()) {
            System.out.println("Cineplex initialization successful");
          } else {
            System.out.println("Cineplex initialization unsuccessful");
          }
          break;
        case 2:
          p.printRoute("Cineplex App > Staff > Database > Initialise Movies");
          if (initializeMovies()) {
            System.out.println("Movie initialization successful");
          } else {
            System.out.println("Movie initialization unsuccessful");
          }
          break;
        case 3:
          p.printRoute("Cineplex App > Staff > Database > Initialise Showtimes");
          if (initializeShowtime()) {
            System.out.println("Showtimes initialization successful");
          } else {
            System.out.println("Showtimes initialization unsuccessful");
          }
          break;
        case 4:
          p.printRoute("Cineplex App > Staff > Database > Add Holidays");
          handleAddHoliday();
          break;
        case 5:
          p.printRoute("Cineplex App > Staff > Database > Update Ticket Prices");
          SystemManager.updateTicketPrices();
          break;
        case 6:
          p.printRoute("Cineplex App > Staff > Database > Reset Database");
          if (resetDatabase()) {
            System.out.println("Database cleared");
          }
          break;
        default:
          break;
      }
      if (option != 7) {
        System.out.println();
        Helper.pressAnyKeyToContinue();
        option = Helper.readInt(1, 7);
      }
      
    };
  }

  /**
   * A method that initialize dummy data for Cineplex.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeCineplex() {
    return Database.initializeCineplex();
  }

  /**
   * A method that initialize dummy data for Movies.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeMovies() {
    return Database.initializeMovies();
  }

  /**
   * A method that initialize dummy data for Showtime.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeShowtime() {
    return Database.initializeShowtime();
  }

  /**
   * A method that initialize dummy data for Showtime.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean handleAddHoliday() {
    String date;
    do {
      date = Helper.setDate(false, true);
    } while (date.equals(""));
    if (SystemManager.addHoliday(date)) {
      return true;
    }
    return false;
  }

  /**
   * A method that reset the database.
   * 
   * @return {@code true} if reset successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more details.
   */
  public static boolean resetDatabase() { // To be changed back to private boolean
    if (Helper.confirm("reset the database")) {
      return Database.resetDatabase();
    } else {
      return false;
    }
  }
}