package src.view;
import src.controller.UserManager;
import src.helper.Helper;


public class StaffView extends MainView {
    
    public String path;

    public StaffView(String pWay) {
        super();

        this.path = pWay;   
    }

    // Print menu selection//
    public void printMenu() {

        Helper.clearScreen();

        printRoute(this.path + " > Staff");

        System.out.println("Please select any of the options given below");
        System.out.println("Option (1): Manage Cineplex selection");
        System.out.println("Option (2): Manage Movies selection");
        System.out.println("Option (3): Manage Showtimes selection");
        System.out.println("Option (4): Configuration of Database & System Settings");
        System.out.println("Option (5): Exit ");
    }

    
    // View app selection//
    public void viewApp() {

        int selection = -1;
        do {

            this.printMenu();

            selection = Helper.readInt(1, 5);

            switch (selection) {
                case 1:
                    CineplexView cineplexView = new CineplexView(this.path + " > Staff", true, "");
                    cineplexView.viewApp();
                    continue;
                case 2:
                    MovieView movieView = new MovieView(this.path + " > Staff", true, "");
                    movieView.viewApp();
                    continue;
                case 3:
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff", "");
                    showtimeView.viewApp();
                    continue;
                case 4:
                    DatabaseView databaseView = new DatabaseView();
                    databaseView.viewApp();
                    continue;
                case 5:
                    break;
                default:
                    break;
            }
        } while (selection != 5);
    }
}
