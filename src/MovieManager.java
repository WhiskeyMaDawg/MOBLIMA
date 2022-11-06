package controller;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import database.*;
import Helper.Helper;
import model.*;
import model.enums.*;
import MobView.ReviewView;

public class MovieManager {

    public static int getTotalNumOfMovie() {
        return Database.numOfMovies;
    }

    public static ArrayList<Movie> getBookableMovies() {
        ArrayList<Movie> showingMovieList = new ArrayList<Movie>();
        for (Movie movie : Database.MOVIES.values()) {
            if (movie.getStatus() == ShowStatus.NOW_SHOWING || movie.getStatus() == ShowStatus.PREVIEW) {
                showingMovieList.add(movie);
            }
        }
        return showingMovieList;
    }

    public static ArrayList<Movie> getComingSoonMovies() {
        ArrayList<Movie> upcomingMovieList = new ArrayList<Movie>();
        for (Movie movie : Database.MOVIES.values()) {
            if (movie.getStatus() == ShowStatus.COMING_SOON) {
                upcomingMovieList.add(movie);
            }
        }
        return upcomingMovieList;
    }


    public static void initMovies() {
        MovieManager.addMovie("Black Adam", ShowStatus.COMING_SOON, "Wakanda", "Alicia",
                new String[] { "The Rock" }, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("The girl who puts powder", ShowStatus.NOW_SHOWING, "My nose not sharp enough.", "Quents",
                new String[] { "LSP" }, MoviesType.THREE_D);
        MovieManager.addMovie("Never Gonna Give You Up", ShowStatus.NOW_SHOWING, "Get Rick Rolled!", "Rick Astley", new String[]{"Rick Astley"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Jumanji", ShowStatus.END_OF_SHOWING, "1995 American fantasy adventure film.", "Joe Johnston", 
            new String[] {"Jonathan Hensleigh", "Greg Taylor", "Jim Strain"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Titanic", ShowStatus.END_OF_SHOWING, "When she boards the Titanic, she meets Jack Dawson, an artist, and falls in love with him.", 
        "Lim Shen Hong", new String[]{"Phylicia","Shen Hong"}, MoviesType.TWO_D);
        MovieManager.addMovie("Goal", ShowStatus.END_OF_SHOWING, "Santiago dreams of playing professional soccer but his father wants him to support the family and earn his livelihood.",
         "Danny Cannon", new String[]  {"Kuno Becker", "David Beckham", "Zinedine Zidane"}, MoviesType.TWO_D);
        MovieManager.addMovie("Goal 2", ShowStatus.NOW_SHOWING, "Newcastle United soccer star Santiago Munez lands a deal to play for Real Madrid. ",
         "Jaume Collet-Serra", new String[]  {"Kuno Becker", "Alessandro Nivola"}, MoviesType.TWO_D);
        MovieManager.addMovie("Goal 3", ShowStatus.COMING_SOON, "Follow Santiago Munez and his best friends, Liam Adams and Charlie Braithwaite, as they prepare for the FIFA World Cup Finals.",
         "Danny Cannon", new String[]  {"Kuno Becker", "Leo Gregory", "Kasia Smutniak"}, MoviesType.TWO_D);
        MovieManager.addMovie("The Bourne Identity", ShowStatus.END_OF_SHOWING, "A man with a bullet-ridden body is found and looked after by strangers.", 
        "Doug Liman", new String[] {"Matt Damon", "Brian Cox", "Chris Cooper"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("The Bourne Supremacy", ShowStatus.NOW_SHOWING, "When he is falsely framed in a CIA operation, Jason Bourne is forced to return to his old ways as an assassin, in order to figure out why they are still after him.", 
        "Paul Greengrass", new String[] {"Matt Damon", "Brian Cox", "Julia Stiles"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("The Bourne Ultimatum", ShowStatus.NOW_SHOWING, "Jason Bourne sets off on a quest to uncover his dark past.", 
        "Paul Greengrass", new String[] {"Matt Damon", "Paddy Considine", "Julia Stiles"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("The Bourne Legacy", ShowStatus.COMING_SOON, "The US Department of Defense, which runs covert operations, offers stimulants to its field operatives for better results.", 
        "Tony Gilroy", new String[] {"Matt Damon", "Brian Cox", "Jeremy Renner"}, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Jason Bourne", ShowStatus.NOW_SHOWING, "Jason Bourne, a former CIA agent, is drawn out of hiding by CIA director Robert Dewey.", 
        "Paul Greengrass", new String[] {"Matt Damon", "Vincent Cassel", "Julia Stiles"}, MoviesType.BLOCKBUSTER);
    }


    public static void displayMovieDetails(Movie movie) {
        System.out.println();
        System.out.println(String.format("%-30s", "").replace(" ", "-"));
        System.out.println(String.format("%-15s: %s", "Movie ID", movie.getMovieId()));
        System.out.println(String.format("%-15s: %s", "Title", movie.getTitle()));
        System.out.println(String.format("%-15s: %s", "Show Status", movie.getStatus().name()));
        System.out.println(String.format("%-15s: %s", "Movie Type", movie.getType().name()));
        String[] castMembers = movie.getCast();
        String cast = String.join(", ", castMembers);
        System.out.println(String.format("%-15s: %s", "Director", movie.getDirector()));
        System.out.println(String.format("%-15s: %s", "Cast", cast));
        System.out.println(String.format("%-15s: %s", "Synopsis", movie.getSynopsis()));
        System.out.println(String.format("%-15s: %s", "Number of Ticket Sales", movie.getTicketSales()));
        System.out.println(String.format("%-15s: %s", "Overall Rating",
                movie.getReviews().size() <= 1 ? "NA" : Helper.df1.format(movie.getOverallRating())));
        System.out.println(String.format("%-30s", ""));
        System.out.println();
    }

    public static void addMovie(String title, ShowStatus status, String synopsis, String director,
            String[] cast, MoviesType type) {
        int mId = Helper.generateUniqueId(Database.MOVIES);
        String movieId = String.format("M%04d", mId);

        //double TWODMovieDefaultPrice = Database.PRICES.get(MoviesType.TWO_D);
        
        //double THREEDMovieDefaultPrice = Database.PRICES.get(MoviesType.THREE_D);

        //double BlockbusterMovieDefaultPrice = Database.PRICES.get(MoviesType.BLOCKBUSTER);


        Movie newMovie;
        System.out.println("Test4");
        switch (type) {
            case TWO_D:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, MoviesType.TWO_D,
                        13.00);
                break;
            case THREE_D:
                newMovie = new ThreeDMovie(movieId, title, status, synopsis, director, cast, MoviesType.THREE_D,
                        20.00);
                break;
            case BLOCKBUSTER:
                newMovie = new BlockbusterMovie(movieId, title, status, synopsis, director, cast,
                        MoviesType.BLOCKBUSTER,
                        16.00);
                break;
            default:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, MoviesType.TWO_D,
                        13.00);
                break;
        }

        Database.MOVIES.put(movieId, newMovie);
        Database.saveFile(FileType.MOVIES);
        Database.numOfMovies+=1;
        System.out.println("Movie created! Movie Details: ");
        MovieManager.displayMovieDetails(newMovie);
    }
    public static void removeMovie() {
        int option = Integer.MIN_VALUE;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to remove ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            option = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (option != MovieManager.getTotalNumOfMovie() + 1) {
                Movie oldMovie = MovieManager.getAllMovieList().get(option - 1);
                Database.MOVIES.remove(oldMovie.getMovieId());
                ShowtimeManager.removeShowtimeByMovie(oldMovie);
                Database.saveFile(FileType.MOVIES);
                Database.numOfMovies-=1;
                System.out.println("Movie has been removed!");
            }
        }
    }

    
    public static boolean displayListOfBookableMovies() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("Sorry, we don't have any showing movies at this time.");
            return false;
        } 
        else {
            System.out.println("List of Movies available for booking: ");
            for (int i = 0; i < MovieManager.getBookableMovies().size(); i++) {
                System.out.println("(" + (i + 1) + ") " + MovieManager.getBookableMovies().get(i).getTitle());
            }
        }
        System.out.println();
        return true;
    }

    public static Movie selectMovie() {
        System.out.println("Select a movie by entering the corresponding index:");
        int choice = Helper.readInt(1, (MovieManager.getBookableMovies().size() + 1));
        Movie selectedMovie = MovieManager.getBookableMovies().get(choice - 1);
        System.out.print("\nYou selected: ");
        MovieManager.displayMovieDetails(selectedMovie);
        System.out.println();
        Helper.pressAnyKeyToContinue();
        return selectedMovie;
    }

    public static void handleViewPastMovieReviews(String path) {
        Movie selectedMovie = MovieManager.selectMovie();
        ReviewView reviewView = new ReviewView(selectedMovie, path);
        reviewView.applicationView();
    }

    public static void updateMovie() {
        int option = Integer.MIN_VALUE;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to update ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            option = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (option != MovieManager.getTotalNumOfMovie() + 1) {
                Movie movie = MovieManager.getAllMovieList().get(option - 1);
                String movieId = movie.getMovieId();
                System.out.println("\nUpdate Show Status to: ");
                System.out.println("Select show status: ");
                int count = 0;
                for (ShowStatus status : ShowStatus.values()) {
                    count += 1;
                    System.out.println("(" + (count) + ") " + status);
                }
                option = Helper.readInt(1, count);
                ShowStatus newShowStatus = ShowStatus.values()[option - 1];
                movie.setStatus(newShowStatus);
                Database.MOVIES.remove(movie.getMovieId());
                Database.MOVIES.put(movieId, movie);
                Database.saveFile(FileType.MOVIES);
                System.out.println("Successfully updated show status");
            }
        }
    }

    public static void printTop5ByTicketSales() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("No movies found!");
            return;
        }

        ArrayList<Movie> movieList = MovieManager.getBookableMovies();
        Collections.sort(movieList, (a, b) -> {return a.compareToTicketSales(b);});
        Collections.sort(movieList,Collections.reverseOrder());
        List<Movie> res = movieList.subList(0,5);

        System.out.println("Top 5 Movies by Ticket Sales: ");
        for (int i = 5; i > 0; i--) {
            System.out.println(String.format("(%d) %-30s: %s", (5 - i + 1), res.get(i - 1).getTitle(),
                    res.get(i - 1).getTicketSales()));
        }
    }

    public static void printTop5ByOverallRating() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("No movies found!");
            return;
        }

        ArrayList<Movie> movieList = MovieManager.getBookableMovies();
        Collections.sort(movieList,Collections.reverseOrder());
        List<Movie> res = movieList.subList(0, 5);

        System.out.println("Top 5 Movies by Overall Rating: ");
        for (int i = 5; i > 0; i--) {
            System.out.println(String.format("(%d) %-20s: %s", (5 - i + 1), res.get(i - 1).getTitle(),
                    res.get(i - 1).getOverallRating() == -1 ? "N/A" : res.get(i - 1).getOverallRating()));
        }
    }

    public static ArrayList<Movie> getAllMovieList() {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        movieList.addAll(MovieManager.getBookableMovies());
        movieList.addAll(MovieManager.getComingSoonMovies());
        return movieList;
    }

    public static void displayExistingMovies() {
        if(MovieManager.getTotalNumOfMovie()==0){
            System.out.println("No movies available for screening.");
        }
        else{System.out.println("Current Movie(s) available for screening: ");
            for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
                System.out.println("(" + (i + 1) + ") " + MovieManager.getAllMovieList().get(i).getTitle() + " ["
                        + MovieManager.getAllMovieList().get(i).getStatus() + "]");
            }
        }
    }

    public static void displayReviews(Movie movie) {
        ArrayList<Review> reviews = movie.getReviews();
        if (reviews.size() == 0) {
            System.out.println("No reviews found.");
            return;
        }

        for (Review review : reviews) {
            System.out.println();
            System.out.println(String.format("%-30s", ""));
            System.out.println(String.format("%-15s: %s", "Rating", Helper.df1.format(review.getRating())));
            System.out.println(String.format("%-15s: %s", "Review", review.getReview()));
            System.out.println(String.format("%-30s", ""));
            System.out.println();
        }
    }

    public static void addReview(Movie movie, double rating, String review) {
        Review newReview = new Review(review, rating);
        movie.addReview(newReview);
        Database.MOVIES.put(movie.getMovieId(), movie);
        System.out.println("Successfully added review!");
    }

    public static void onAddMovie() {
        System.out.println("Enter movie title: ");
        String title = Helper.readString();

        System.out.println("\nSelect show status: ");
        int count = 0;
        for (ShowStatus status : ShowStatus.values()) {
            count += 1;
            System.out.println("(" + (count) + ") " + status);
        }
        int option = Helper.readInt(1, count);
        ShowStatus showStatus = ShowStatus.values()[option - 1];

        System.out.println("\nEnter synopsis: ");
        String synopsis = Helper.readString();

        System.out.println("\nEnter director's name: ");
        String director = Helper.readString();

        System.out.println("\nEnter cast member names line-by-line: (Enter '0' to stop)");
        ArrayList<String> castMembers = new ArrayList<String>();
        String castMember = Helper.readString();
        do {
            if (!Helper.isNumeric(castMember)) {
                castMembers.add(castMember);
            }
            castMember = Helper.readString();
        } while (!castMember.equals("0") && castMembers.size() < 2);

        String[] cast = new String[castMembers.size()];
        cast = castMembers.toArray(cast);

        System.out.println("\nEnter movie type: ");
        count = 0;
        for (MoviesType type : MoviesType.values()) {
            System.out.println("(" + (++count) + ") " + type);
        }
        option = Helper.readInt(1, count);
        MoviesType movieType = MoviesType.values()[option - 1];
        

        MovieManager.addMovie(title, showStatus, synopsis, director, cast, movieType);
    }

    public static void displayMovieBasedOnStatus(ShowStatus status) {
        ArrayList<Movie> movies = MovieManager.getAllMovieList();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getStatus() == status) {
                MovieManager.displayMovieDetails(movies.get(i));
            }
        }
    }

    public static boolean handleBookMovie() {
        System.out.println("Which movie would you like to book?");
        if (MovieManager.displayListOfBookableMovies()) {
            return true;
        }
        return false;
    }

    public static void handleTop5Movies() {
        // 0 - default
        // 1 - ticket sales only
        // 2 - rating only
        int currentStatus = Integer.parseInt(Database.SYSTEM); // get status from database
        if (currentStatus == 1) {
            MovieManager.printTop5ByTicketSales();
        } else if (currentStatus == 2) {
            MovieManager.printTop5ByOverallRating();
        } else {
            System.out.println("(1) List Top 5 Movies by Ticket Sales");
            System.out.println("(2) List Top 5 Movies by Overall Rating");
            System.out.println("Which option would you like to select?");
            int option = Helper.readInt(1, 2);
            if (option == 1) {
                System.out.println();
                currentStatus=1;
                MovieManager.printTop5ByTicketSales();
            } else {
                System.out.println();
                currentStatus=2;
                MovieManager.printTop5ByOverallRating();
            }
        }
    }

    public static void setViewableTop5() {
        // 0 - default
        // 1 - ticket sales only
        // 2 - rating only
        System.out.println("(1) Show Top 5 Movies by Ticket Sales");
        System.out.println("(2) Show Top 5 Movies by Overall Rating");
        System.out.println("Which option would you like to select?");
        int option = Helper.readInt(1, 2);
        switch (option) {
            case 1:
                Database.SYSTEM = "1";
                Database.saveFile(FileType.SYSTEM);
                break;
            case 2:
                Database.SYSTEM = "2";
                Database.saveFile(FileType.SYSTEM);
                break;
            default:
                Database.SYSTEM = "0";
                Database.saveFile(FileType.SYSTEM);
                break;
        }
        System.out.println("System has been updated!");
    }
}
