package MobView;
import Helper.Helper;
import controller.ShowtimeManager;
import model.*;


//ShowtimeView provides the view to manage and select showtime
public class ShowtimeView implements ViewInterface {
	
    public String path; //path of entry for showtime view
    private String username; //name of current user

    /**
     * Overrided contructor for the ShowtimeView
     * 
     * @param path     of entry for StaffView
     * @param username of current user
     */
    public ShowtimeView(String path, String username) {
        this.path = path;
        this.username = username;
    }

    public void printOptions() { //view menu
    	printPath p = new printPath();
        Helper.clearScreen();
        p.printRoute(this.path + " > Showtime");
        System.out.println("What would you like to do ?");
        System.out.println("1: Create Showtime");
        System.out.println("2: Remove Showtime");
        System.out.println("3: Update Showtime");
        System.out.println("4: List Showtimes");
        System.out.println("5: Exit");
    }

    public void applicationView() { //view app
        int option;
        printPath p = new printPath();
        do {
            printOptions();
            option = Helper.readInt(1, 5);
            switch (option) {
                case 1:
                    Helper.clearScreen();
                    p.printRoute(path + " --> Create Showtime");
                    handleCreateShowtime();
                    
                    Helper.pressAnyKeyToContinue();
                    break;
                case 2:
                    Helper.clearScreen();
                    p.printRoute(path + " --> Remove Showtime");
                    if (ShowtimeManager.removeShowtime()) {
                        System.out.println("\nRemoved showtime successfully!");
                    } else {
                        System.out.println("\nFailed to remove showtime!");
                    }
                    
                    Helper.pressAnyKeyToContinue();
                    break;
                case 3:
                    Helper.clearScreen();
                    p.printRoute(path + " > Update Showtime");
                    if (ShowtimeManager.updateShowtime()) {
                        System.out.println("\nUpdated showtime successfully!");
                    } else {
                        System.out.println("\nFailed to update showtime!");
                    }
                    
                    Helper.pressAnyKeyToContinue();
                    break;
                case 4:
                    Helper.clearScreen();
                    p.printRoute(this.path + " > Showtime > Showtime Listing");
                    ShowtimeManager.displayAllShowtime();
                    
                    Helper.pressAnyKeyToContinue();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (option != 5);
    }

    /**
     * Overrided View App - from movie view (MovieGoer)
     * 
     * @param movie to select showtime from
     */
    protected void applicationView(Movie movie) {
        Helper.clearScreen();
        printPath p = new printPath();
        p.printRoute(path + " --> " + movie.getTitle());
        ShowtimeManager.handleShowtimeSelection(movie, this.username);
        return;
    }

    /**
     * Overrided View App - from cineplex view (MovieGoer)
     * 
     * @param cineplex to select showtime from
     */
    protected void applicationView(Cineplex cineplex) {
    	printPath p = new printPath();
        Helper.clearScreen();
        p.printRoute(path + " --> " + cineplex.getLocation());
        ShowtimeManager.handleShowtimeSelection(cineplex, this.username);
        return;
    }

    /**
     * Action function to handle creating a showtime
     */
    private void handleCreateShowtime() {
        if (ShowtimeManager.onCreateShowtime()) System.out.println("\nSuccessfully created showtime!");
        else System.out.println("\nUnsuccessful attempt at creating showtime!");
    }

}
