package src.view;
import src.controller.SystemManager;
import src.database.Database;
import src.helper.Helper;


public class DatabaseView extends MainView {
  public DatabaseView() {
    super();
  }

  public void printMenu() {
    Helper.clearScreen();

    printRoute(" Cineplex App >> Staff >> Database");
    System.out.println("Please select any of the options given below");
    System.out.println("Option (1): Initialization of Cineplex");
    System.out.println("Option (2): Initialization of Movies");
    System.out.println("Option (3): Initialization of Showtimes");
    System.out.println("Option (4): Addition of Holidays");
    System.out.println("Option (5): Update prices of tickets");
    System.out.println("Option (6): Reset Database information");
    System.out.println("Option (7): Exit menu");
  }

 
  public void viewApp() {

    int Num = -1;
    do {

      printMenu();
      Num = Helper.readInt(1, 7);

      System.out.println();
      
      switch (Num) {
        case 1:
          printRoute("Cineplex App > Staff > Database > Initialise Cineplex");
          if (initializeCineplex()) {
            System.out.println("Initialization of Cineplex successful");
          } else {
            System.out.println("Initialization of Cineplex Unsuccessful");
          }
          break;
        case 2:
          printRoute("Cineplex App > Staff > Database > Initialise Movies");
          if (initializeMovies()) {
            System.out.println("Initialization of Movie successful");
          } else {
            System.out.println("Initialization of Movie Unsuccessful");
          }
          break;
        case 3:
          printRoute("Cineplex App > Staff > Database > Initialise Showtimes");
          if (initializeShowtime()) {
            System.out.println("Initialization of Showtimes successful");
          } else {
            System.out.println("Initialization of Showtimes Unsuccessful");
          }
          break;
        case 4:
          printRoute("Cineplex App > Staff > Database > Add Holidays");
          handleAddHoliday();
          break;
        case 5:
          printRoute("Cineplex App > Staff > Database > Update Ticket Prices");
          SystemManager.updateTicketPrices();
          break;
        case 6:
          printRoute("Cineplex App > Staff > Database > Reset Database");
          if (resetDatabase()) {
            System.out.println("Database System cleared");
          }
          break;
        default:
          break;
      }
      if (Num != 7) {
        System.out.println();
        Helper.pressAnyKeyToContinue();
      }

    } while (Num != 7);

  }

 
  private boolean initializeCineplex() {
    return Database.initializeCineplex();
  }
  private boolean initializeMovies() {
    return Database.initializeMovies();
  }
  private boolean initializeShowtime() {
    return Database.initializeShowtime();
  }
 
  private boolean handleAddHoliday() {

    String arrange;
    do {
      arrange = Helper.setDate(false, true);
    }   while (arrange.equals(""));
    
    if (SystemManager.addHoliday(arrange)) {
      return true;
    }
    return false;
  }

  public static boolean resetDatabase() { 

    if (Helper.promptConfirmation("Reset database system")) {
      return Database.clearDatabase();
    } 
    else {
      return false;
    }
  }
}