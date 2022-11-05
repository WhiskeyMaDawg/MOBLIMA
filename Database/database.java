package Database;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;

import src.controller.CineplexManager;
import src.controller.MovieManager;
import src.controller.ShowtimeManager;
import src.controller.SystemManager;
import src.controller.UserManager;
import src.model.Showtime;

//Database class to write object into serializable file (.dat)

public class database {

    public HashMap<String, User> USERS = new HashMap<String, User>();

    public HashMap<String, Booking> BOOKINGS = new HashMap<String, Booking>();

    public HashMap<String, Cineplex> CINEPLEX = new HashMap<String, Cineplex>();

    public HashMap<String, Showtime> SHOWTIME = new HashMap<String, Showtime>();
    
    public HashMap<String, Movie> MOVIES = new HashMap<String, Movie>();

    public HashMap<MoviesType, Double> PRICES = new HashMap<MoviesType, Double>();

    public HashSet<String> HOLIDAYS = new HashSet<String>();

    public String SYSTEM = new String();

    public int numOfMovies = 0;

    public int numOfShowtimes = 0;
    
    //Constructor that reads all the data file during initialisation of programme
    public database(){
        int i;
        for (Filetype fileType : FileType.values()){
            if (readSerialisedObject(fileType) == null){
                System.out.println("Reading " + fileType + " failed!");
            }
        }
    }

    //function to determine the filetype
    public String determineClass(Object object){
        Class c = object.getClass();
        String cStr = c.getName();
        cStr = cStr.substring(cStr.lastIndexOf('.')+1);
        return cStr;
    }

    //save a particular filetype into database
    public void saveFile(Object object){
        serializeObject(object);

    }

    //save all files into the database
    public void saveAllFiles(){
        for (FileType filetype : filetype.values()){
            serializeObject(filetype);
        }
    }

    //Serialise object into .dat files
    //need to change the dir
    public void serializeObject(Object object){
        try{
            String classType = determineClass(object);
            System.out.println("Class name is : " + classType);
            String path = "C:/Users/wloon/Desktop/NTU/Y2S1/SC2002 Object Oriented Design & Programming/Assignment/Database/" + classType + ".dat";
            FileOutputStream fis = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fis);
            if (object == FileType.USERS) {
                out.writeObject(USERS);
              } else if (object == FileType.BOOKINGS) {
                out.writeObject(BOOKINGS);
              } else if (object == FileType.CINEPLEX) {
                out.writeObject(CINEPLEX);
              } else if (object == FileType.SHOWTIME) {
                out.writeObject(SHOWTIME);
              } else if (object == FileType.MOVIES) {
                out.writeObject(MOVIES);
              } else if (object == FileType.PRICES) {
                out.writeObject(PRICES);
              } else if (object == FileType.HOLIDAYS) {
                out.writeObject(HOLIDAYS);
              } else if (object == FileType.SYSTEM) {
                out.writeObject(SYSTEM);
              }
            //out.writeObject(object);
            out.close();
            fis.close();
            //System.out.println("Serialised object name: " + filename);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //need to change the dir
    public Object readSerialisedObject(Object obj){
        try {
            String classType = determineClass(obj);
            System.out.println("Class name is : " + classType);
            String path = "C:/Users/wloon/Desktop/NTU/Y2S1/SC2002 Object Oriented Design & Programming/Assignment/Database/" + classType + ".dat";
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(bis);
            Object object = in.readObject();

            //save into database
            if (obj == FileType.USERS) {
                USERS = (HashMap<String, User>) object;
              } else if (obj == FileType.BOOKINGS) {
                BOOKINGS = (HashMap<String, Booking>) object;
              } else if (obj == FileType.CINEPLEX) {
                CINEPLEX = (HashMap<String, Cineplex>) object;
              } else if (obj == FileType.SHOWTIME) {
                SHOWTIME = (HashMap<String, Showtime>) object;
                numOfShowtimes = SHOWTIME.size();
              } else if (obj == FileType.MOVIES) {
                MOVIES = (HashMap<String, Movie>) object;
                numOfMovies = MOVIES.size();
              } else if (obj == FileType.PRICES) {
                PRICES = (HashMap<MoviesType, Double>) object;
              } else if (obj == FileType.HOLIDAYS) {
                HOLIDAYS = (HashSet<String>) object;
              } else if (obj == FileType.SYSTEM) {
                SYSTEM = (String) object;
              }

            in.close();
            System.out.println("Deserialised!");
            return object;
        }
        catch (IOException ex) {
            System.out.println("IOException caught!");
			ex.printStackTrace();
            return null;
		} catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound exception caught!");
			ex.printStackTrace();
            return null;
		}
    }

    //Intialise staff when dataset is empty
    public boolean initStaff(){
        if (USERS.size() != 0){
            System.out.println("The database already has data!");
            return false;
        }

        UserManager.initStaff();
        return true;
    }

    //Initialise cineplex
    public boolean initCineplex(){
        if (CINEPLEX.size() != 0){
            System.out.println("The database already has data!");
            return false;
        }
        CineplexManager.initCineplex();
        return true;
    }

    //Initialise movies
    public boolean initMovies(){
        if (Movies.size() != 0){
            System.out.println("The databse already has data!");
            return false;
        }
        MovieManager initMovies();
        numOfMovies = MOVIES.size();
        return true;
    }

    //Initialise showtime
    public boolean initShowtime(){
        if (SHOWTIME.size() != 0){
            System.out.println("The database already has data");
            return false;
        }
        ShowtimeManager.initShowtime();
        numOfShowtimes = SHOWTIME.size();
        return true;
    }
    
    //Initialise Public Holidays
    public boolean initHoliday(){
        if (HOLIDAYS.size() != 0){
            System.out.println("The database already has data!");
            return false;
        }
        SystemManager.initHoliday();
        return true;
    }

    //Reset the database
    public boolean resetDatabase(){
        USERS = new HashMap<String, User>();
        Database.initializeStaff();
        serializeObject(FileType.USERS);

        BOOKINGS = new HashMap<String, Booking>();
        serializeObject(FileType.BOOKINGS);

        CINEPLEX = new HashMap<String, Cineplex>();
        serializeObject(FileType.CINEPLEX);

        SHOWTIME = new HashMap<String, Showtime>();
        numOfShowtimes = 0;
        serializeObject(FileType.SHOWTIME);

        MOVIES = new HashMap<String, Movie>();
        numOfMovies = 0;
        serializeObject(FileType.MOVIES);

        PRICES = new HashMap<MoviesType, Double>();
        PRICES.put(MoviesType.TWO_D, 13.00);
        PRICES.put(MoviesType.THREE_D, 20.00);
        PRICES.put(MoviesType.BLOCKBUSTER, 16.00);
        serializeObject(FileType.PRICES);

        HOLIDAYS = new HashSet<String>();
        Database.initializeHoliday();
        serializeObject(FileType.HOLIDAYS);

        SYSTEM = new String("0");
        serializeObject(FileType.SYSTEM);

        return true;    
    }

    // public static void main(String[] args) {
    //     Professor prof = new Professor("JiaWen", "Jlee242", 999);
    //     database obj = new database();
    //     obj.serializeObject(prof);
    // }
}

