package MobView;
import Helper.Helper;
import model.*;
import controller.*;

public class ReviewView implements ViewInterface {

    private String path;

    private Movie movie;

  
    public ReviewView(Movie movie, String path) {
        this.movie = movie;
        this.path = path;
    }

    
    // print menu//
    public void printOptions() {

        Helper.clearScreen();
        printPath p = new printPath();
        
        p.printRoute(this.path + " >> REVIEWS >> " + this.movie.getTitle());
        
        System.out.println("Please select any of the options given below");
        System.out.println("Option 1: View previous reviews given");
        System.out.println("Option 2: Give a new movie review");
        System.out.println("Option 3: Exit");
    }

  
    //menu to view app//
    public void applicationView() {

        int selection = -1;

        do {
            this.printOptions();

            selection = Helper.readInt(1, 3);

            switch (selection) {
                case 1:

                    System.out.println("\nReviews:");
                    MovieManager.displayReviews(this.movie);
                    break;
                case 2:
                    System.out.println("Please give your review rating (1.0[Worst] to 5.0[Best]):");
                    double movieRate = Helper.readDouble(1, 5);
                    System.out.println("Please enter your review (In words):");
                    String InputOfReview = Helper.readString();
                    MovieManager.addReview(this.movie, movieRate, InputOfReview);
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            if (selection != 3) {

                Helper.pressAnyKeyToContinue();
            }
        } while (selection != 3);
    }
}
