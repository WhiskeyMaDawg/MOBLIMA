package MobView;
import database.Database;
import Helper.Helper;


import controller.*;
import model.*;
import model.enums.*;

public class CineplexView implements ViewInterface {
    /**
     * Displays the application path
     */
    private String path;

    /**
     * indicates if current user is staff or not
     */
    private boolean isStaff;

    /**
     * Username of current user (staff / movie goer)
     */
    private String username;

    public CineplexView(String path, boolean isStaff, String username) {
        //super();
        this.path = path;
        this.isStaff = isStaff;
        this.username = username;
    }

    public void printOptions() {
    	
    	printPath p = new printPath();
    	
        if (this.isStaff) {
            Helper.clearScreen();

            p.printRoute(this.path + " --> Cineplex");
            System.out.println("Which would you like to do ?");
            System.out.println("1: Add Cineplex");
            System.out.println("2: Remove Cineplex");
            System.out.println("3: Exit");
        } else {
            Helper.clearScreen();
            p.printRoute(this.path + " --> Cineplex");
            
            if (CineplexManager.getTotalNumOfCineplex() == 0) {
                System.out.println("There are no available cineplexes");
                System.out.println("1: Exit");
            } else {
                int numCin = CineplexManager.getTotalNumOfCineplex();
                CineplexManager.displayExistingCineplex();
                System.out.println("(" + (numCin + 1) + ") Exit");
                System.out.println("Choose the cineplex location");
            }
        }
    }

    public void applicationView() {
    	
    	printPath p = new printPath();
    	
        int numCin = CineplexManager.getTotalNumOfCineplex();
        int choice = -1;
        if (this.isStaff) {
            do {
                this.printOptions();
                numCin = CineplexManager.getTotalNumOfCineplex();
                choice = Helper.readInt(1, 3);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        p.printRoute(this.path + " --> Cineplex --> Add New Cineplex");
                        CineplexManager.handleAddCineplex();
                        break;
                    case 2:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Cineplex > Remove Cineplex");
                        CineplexManager.handleRemoveCineplex();
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                if (choice != 3) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 3);
        }

        else {
            do {
                this.printOptions();
                choice = Helper.readInt(1, numCin + 1);
                if (choice != numCin + 1) {
                    System.out.println(
                            "\n" + CineplexManager.getCineplexList().get(choice - 1).getLocation() + " selected");
                    Helper.pressAnyKeyToContinue();
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " --> Showtimes", this.username);
                    showtimeView.applicationView(CineplexManager.getCineplexList().get(choice - 1));
                }
            } while (choice != (numCin + 1));
        }
    }
}

