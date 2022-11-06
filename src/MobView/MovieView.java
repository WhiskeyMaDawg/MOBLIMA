package MobView;
import database.Database;
import Helper.Helper;
import controller.*;
import model.enums.*;

public class MovieView implements ViewInterface {
    
    private String path; //path of entry for MovieView
    private boolean isStaff; //true if current user is a staff
    private String username; //name of current user


    public MovieView(String path, boolean isStaff, String username) {
        super();
        this.path = path;
        this.isStaff = isStaff;
        this.username = username;
    }

    public void printOptions() { //menu for MovieView
        Helper.clearScreen();
        printPath p = new printPath();
        p.printRoute(this.path + " > Movie");
        System.out.println("What would you like to do ?");
        if (this.isStaff) { //menu for staff
            System.out.println("(1) Add Movie");
            System.out.println("(2) Update Movie");
            System.out.println("(3) Remove Movie");
            System.out.println("(4) List \"NOW SHOWING\" Movies");
            System.out.println("(5) List \"PREVIEW\" Movies");
            System.out.println("(6) List \"COMING SOON\" Movies");
            System.out.println("(7) List Top 5 Movies by Ticket Sales");
            System.out.println("(8) List Top 5 Movies by Overall Rating");
            System.out.println("(9) Show/Hide Top 5 Movies");
            System.out.println("(10) Exit");
        } else { //menu for moviegoers
            System.out.println("(1) Book Movie");
            System.out.println("(2) View Past Movie Reviews");
            System.out.println("(3) List \"NOW SHOWING\" Movies");
            System.out.println("(4) List \"PREVIEW\" Movies");
            System.out.println("(5) List \"COMING SOON\" Movies");
            System.out.println("(6) List Top 5 Movies");
            System.out.println("(7) Exit");
        }
    }

    public void applicationView() {
        int choice;
        printPath p = new printPath();
        if (this.isStaff) { //view app for staff
            do {
                this.printOptions();
                choice = Helper.readInt(1, 10);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Add Movie");
                        MovieManager.onAddMovie();
                        break;
                    case 2:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Update Movie");
                        MovieManager.updateMovie();
                        break;
                    case 3:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Remove Movie");
                        MovieManager.removeMovie();
                        break;
                    case 4:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 5:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 6:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 7:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 8:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 9:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Show/Hide Top 5 Movies");
                        MovieManager.setViewableTop5();
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
                if (1<=choice && choice<10) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 10);
        }

        else { //view app for moviegoers
            do {
                this.printOptions();
                choice = Helper.readInt(1, 7);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Book Movie");
                        if (MovieManager.handleBookMovie()) {
                            ShowtimeView showtimeView = new ShowtimeView(path, this.username);
                            showtimeView.applicationView(MovieManager.selectMovie());
                        }
                        break;
                    case 2:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Movie Reviews");
                        MovieManager.displayExistingMovies();
                        MovieManager.handleViewPastMovieReviews(this.path + " > Movie");
                        break;
                    case 3:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 4:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 5:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 6:
                        Helper.clearScreen();
                        p.printRoute(this.path + " > Movie > Top 5 Movies");
                        MovieManager.handleTop5Movies();
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }
                if (1<=choice && choice<7) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 7);
        }
    }
}
