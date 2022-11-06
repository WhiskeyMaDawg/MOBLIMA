package MobView;
import Helper.Helper;

public class StaffView implements ViewInterface {
    
    public String path;

    public StaffView(String pWay) {
        this.path = pWay;   
    }

    // Print menu selection//
    public void printOptions() {
    	
    	printPath p = new printPath(); 
    	
        Helper.clearScreen();

        p.printRoute(this.path + " --> Staff");

        System.out.println("Please select any of the options given below");
        System.out.println("Option 1: Manage Cineplex selection");
        System.out.println("Option 2: Manage Movies selection");
        System.out.println("Option 3: Manage Showtimes selection");
        System.out.println("Option 4: Configuration of Database & System Settings");
        System.out.println("Option 5: Reset Database");
        System.out.println("Option 6: Exit ");
    }

    
    // View app selection//
    public void applicationView() {
    	printPath p = new printPath();
    	
        int selection = -1;
        do {

            this.printOptions();

            selection = Helper.readInt(1, 6);

            switch (selection) {
                case 1:
                    CineplexView cineplexView = new CineplexView(this.path + " --> Staff", true, "");
                    cineplexView.applicationView();
                    continue;
                case 2:
                    MovieView movieView = new MovieView(this.path + " --> Staff", true, "");
                    movieView.applicationView();
                    continue;
                case 3:
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " --> Staff", "");
                    showtimeView.applicationView();
                    continue;
                case 4:
                    DatabaseView databaseView = new DatabaseView();
                    databaseView.applicationView();
                    continue;
                case 5:
                	System.out.println();
                    p.printRoute("Cineplex App > Staff > Database > Reset Database");
                    if (DatabaseView.resetDatabase()) {
                        System.out.println("Database cleared");
                    }
                    break;
                    
                case 6:
                	break;
                	
                default:
                    break;
            }
        } while (selection != 6);
    }
}
