package database;

import java.util.HashMap;



import java.util.HashSet;

import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import controller.UserManager;
import controller.SystemManager;

import model.*;
import model.enums.MoviesType;
import database.Database;
import database.FileType;
import model.Booking;
import model.Cineplex;
import model.Movie;
import model.Showtime;
import model.Staff;
import model.User;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Database class to write object into serializable file (.dat)

public class Database {
	  /**
	   * The folder name to contain .dat files
	   */
	  private static final String folder = "data";

	  /**
	   * HashMap to contain {@link User} objects
	   */
	  public static HashMap<String, User> USERS = new HashMap<String, User>();

	  /**
	   * HashMap to contain {@link Booking} objects
	   */
	  public static HashMap<String, Booking> BOOKINGS = new HashMap<String, Booking>();

	  /**
	   * HashMap to contain {@link Cineplex} objects
	   */
	  public static HashMap<String, Cineplex> CINEPLEX = new HashMap<String, Cineplex>();

	  /**
	   * HashMap to contain {@link Showtime} objects
	   */
	  public static HashMap<String, Showtime> SHOWTIME = new HashMap<String, Showtime>();

	  /**
	   * HashMap to contain {@link Movie} objects
	   */
	  public static HashMap<String, Movie> MOVIES = new HashMap<String, Movie>();

	  /**
	   * HashMap to contain movie prices according to type
	   */
	  public static HashMap<MoviesType, Double> PRICES = new HashMap<MoviesType, Double>();

	  /**
	   * HashSet to contain dates of holidays
	   */
	  public static HashSet<String> HOLIDAYS = new HashSet<String>();

	  /**
	   * A bit to contain system settings
	   */
	  public static String SYSTEM = new String();

	  /**
	   * Number of movies in database
	   */
	  public static int numOfMovies = 0;

	  /**
	   * Number of showtimes in database
	   */
	  public static int numOfShowtimes = 0;

	  /**
	   * Constructor that reads all the data from the data file during initialization
	   * of program.
	   */
	  public Database() {
		  /*
	    if (!readSerializedObject(FileType.USERS)) {
	      System.out.println("Read into Users failed!");
	    }
	    if (!readSerializedObject(FileType.BOOKINGS)) {
	      System.out.println("Read into Bookings failed!");
	    }
	    if (!readSerializedObject(FileType.CINEPLEX)) {
	      System.out.println("Read into Cineplex failed!");
	    }
	    if (!readSerializedObject(FileType.SHOWTIME)) {
	      System.out.println("Read into Showtime failed!");
	    }
	    if (!readSerializedObject(FileType.MOVIES)) {
	      System.out.println("Read into Movies failed!");
	    }
	    */
		
	    if (!readSerializedObject(FileType.PRICES)) {
	      System.out.println("Read into Prices failed!");
	    }
	    /*
	    if (!readSerializedObject(FileType.HOLIDAYS)) {
	      System.out.println("Read into Holidays failed!");
	    }
	    if (!readSerializedObject(FileType.SYSTEM)) {
	      System.out.println("Read into System failed!");
	    }
	    */
	  }

	  /**
	   * Save a particular {@link FileType} into database.
	   * 
	   * @param fileType file type to be saved.
	   * @see FileType for the different type of filetypes.
	   */
	  public static void saveFile(FileType fileType) {
	    writeSerializedObject(fileType);
	  }

	  /**
	   * Save all files into database.
	   */
	  public static void saveAllFiles() {
	    saveFile(FileType.USERS);
	    saveFile(FileType.BOOKINGS);
	    saveFile(FileType.CINEPLEX);
	    saveFile(FileType.SHOWTIME);
	    saveFile(FileType.MOVIES);
	    saveFile(FileType.PRICES);
	    saveFile(FileType.HOLIDAYS);
	    saveFile(FileType.SYSTEM);
	  }

	  /**
	   * Read serialized object from a particular {@link FileType}.
	   * 
	   * @param fileType file type to be read.
	   * @return {@code true} if read from file is successful. Otherwise,
	   *         {@code false}.
	   */
	  @SuppressWarnings("unchecked")
	  private static boolean readSerializedObject(FileType fileType) {
	    String fileExtension = ".dat";
	    String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
	    try {
	      FileInputStream fileInputStream = new FileInputStream(filePath);
	      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	      Object object = objectInputStream.readObject();
	      if (!(object instanceof HashMap) && !(object instanceof HashSet) && !(object instanceof String)) {
	        System.out.println(fileType.fileName);
	        objectInputStream.close();
	        return false;
	      }

	      // Read into database
	      if (fileType == FileType.USERS) {
	        USERS = (HashMap<String, User>) object;
	      } else if (fileType == FileType.BOOKINGS) {
	        BOOKINGS = (HashMap<String, Booking>) object;
	      } else if (fileType == FileType.CINEPLEX) {
	        CINEPLEX = (HashMap<String, Cineplex>) object;
	      } else if (fileType == FileType.SHOWTIME) {
	        SHOWTIME = (HashMap<String, Showtime>) object;
	        numOfShowtimes = SHOWTIME.size();
	      } else if (fileType == FileType.MOVIES) {
	        MOVIES = (HashMap<String, Movie>) object;
	        numOfMovies = MOVIES.size();
	      } else if (fileType == FileType.PRICES) {
	        PRICES = (HashMap<MoviesType, Double>) object;
	      } else if (fileType == FileType.HOLIDAYS) {
	        HOLIDAYS = (HashSet<String>) object;
	      } else if (fileType == FileType.SYSTEM) {
	        SYSTEM = (String) object;
	      }

	      objectInputStream.close();
	      fileInputStream.close();
	    } catch (EOFException err) {
	      System.out.println("Warning: " + err);
	      if (fileType == FileType.USERS) {
	        USERS = new HashMap<String, User>();
	        Database.initializeStaff();
	      }
	    } catch (IOException err) {
	      err.printStackTrace();
	      return false;
	    } catch (ClassNotFoundException err) {
	      err.printStackTrace();
	      return false;
	    } catch (Exception err) {
	      System.out.println("Error: " + err.getMessage());
	      return false;
	    }
	    return true;
	  }

	  /**
	   * Write serialized object to file
	   * 
	   * @param fileType file type to write into.
	   * @return {@code true} if write to file is successful. Otherwise,
	   *         {@code false}.
	   */
	  private static boolean writeSerializedObject(FileType fileType) {
	    String fileExtension = ".dat";
	    String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
	    try {
	      FileOutputStream fileOutputStream = new FileOutputStream(filePath);
	      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	      if (fileType == FileType.USERS) {
	        objectOutputStream.writeObject(USERS);
	      } else if (fileType == FileType.BOOKINGS) {
	        objectOutputStream.writeObject(BOOKINGS);
	      } else if (fileType == FileType.CINEPLEX) {
	        objectOutputStream.writeObject(CINEPLEX);
	      } else if (fileType == FileType.SHOWTIME) {
	        objectOutputStream.writeObject(SHOWTIME);
	      } else if (fileType == FileType.MOVIES) {
	        objectOutputStream.writeObject(MOVIES);
	      } else if (fileType == FileType.PRICES) {
	        objectOutputStream.writeObject(PRICES);
	      } else if (fileType == FileType.HOLIDAYS) {
	        objectOutputStream.writeObject(HOLIDAYS);
	      } else if (fileType == FileType.SYSTEM) {
	        objectOutputStream.writeObject(SYSTEM);
	      }

	      objectOutputStream.close();
	      fileOutputStream.close();
	      return true;
	    } catch (Exception err) {
	      System.out.println("Error: " + err.getMessage());
	      return false;
	    }
	  }

	  /**
	   * A method to initialize {@link Staff} data when the database is empty.
	   * <p>
	   * Calls {@link UserManager} to initialize the dummy guests.
	   * 
	   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
	   *         database is not empty.
	   */
	  public static boolean initializeStaff() {
	    if (USERS.size() != 0) {
	      System.out.println("The database already has staffs. Reset database first to initialize staffs");
	      return false;
	    }
	    UserManager.initStaff();
	    return true;
	  }

	  /**
	   * A method to initialize {@link Cineplex} data when the database is empty.
	   * <p>
	   * Calls {@link CineplexManager} to initialize the cineplex.
	   * 
	   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
	   *         database is not empty.
	   */
	  public static boolean initializeCineplex() {
	    if (CINEPLEX.size() != 0) {
	      System.out.println("The database already has cineplexes. Reset database first to initialize Cineplex");
	      return false;
	    }
	    CineplexManager.initCineplex();
	    return true;
	  }

	  /**
	   * A method to initialize {@link Movie} data when the database is empty.
	   * <p>
	   * Calls {@link MovieManager} to initialize the cineplex.
	   * 
	   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
	   *         database is not empty.
	   */
	  public static boolean initializeMovies() {
	    if (MOVIES.size() != 0) {
	      System.out.println("The database already has movies. Reset database first to initialize Movies");
	      return false;
	    }
	    MovieManager.initMovies();
	    numOfMovies = MOVIES.size();
	    return true;
	  }

	  /**
	   * A method to initialize {@link Showtime} data when the database is empty.
	   * <p>
	   * Calls {@link ShowtimeManager} to initialize the cineplex.
	   * 
	   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
	   *         database is not empty.
	   */
	  public static boolean initializeShowtime() {
	    if (SHOWTIME.size() != 0) {
	      System.out.println("The database already has showtimes. Reset database first to initialize Showtimes");
	      return false;
	    }
	    ShowtimeManager.initShowtime();
	    numOfShowtimes = SHOWTIME.size();
	    return true;
	  }

	  /**
	   * A method to initialize {@code Holiday} data when the database is empty.
	   * <p>
	   * Calls {@link SystemManager} to initialize the cineplex.
	   * 
	   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
	   *         database is not empty.
	   */
	  public static boolean initializeHoliday() {
	    if (HOLIDAYS.size() != 0) {
	      System.out.println("The database already has holidays. Reset database first to initialize Holidays");
	      return false;
	    }
	    SystemManager.initHoliday();
	    return true;
	  }

	  /**
	   * A method to clear out all the data in database.
	   * 
	   * @return {@code true} if data is cleared successfully.
	   */
	  public static boolean resetDatabase() {
	    // Initialize staff data
	    USERS = new HashMap<String, User>();
	    Database.initializeStaff();
	    writeSerializedObject(FileType.USERS);

	    BOOKINGS = new HashMap<String, Booking>();
	    writeSerializedObject(FileType.BOOKINGS);

	    CINEPLEX = new HashMap<String, Cineplex>();
	    writeSerializedObject(FileType.CINEPLEX);

	    SHOWTIME = new HashMap<String, Showtime>();
	    numOfShowtimes = 0;
	    writeSerializedObject(FileType.SHOWTIME);

	    MOVIES = new HashMap<String, Movie>();
	    numOfMovies = 0;
	    writeSerializedObject(FileType.MOVIES);

	    PRICES = new HashMap<MoviesType, Double>();
	    PRICES.put(MoviesType.TWO_D, 13.00);
	    PRICES.put(MoviesType.THREE_D, 20.00);
	    PRICES.put(MoviesType.BLOCKBUSTER, 16.00);
	    writeSerializedObject(FileType.PRICES);

	    HOLIDAYS = new HashSet<String>();
	    Database.initializeHoliday();
	    writeSerializedObject(FileType.HOLIDAYS);

	    SYSTEM = new String("0");
	    writeSerializedObject(FileType.SYSTEM);

	    return true;
	  }
	}


/*
public class Database {

  public static HashMap<String, User> USERS = new HashMap<String, User>();

  public static HashMap<String, Booking> BOOKINGS = new HashMap<String, Booking>();

  public static HashMap<String, Cineplex> CINEPLEX = new HashMap<String, Cineplex>();

  public static HashMap<String, Showtime> SHOWTIME = new HashMap<String, Showtime>();
  
  public static HashMap<String, Movie> MOVIES = new HashMap<String, Movie>();

  public static HashMap<MoviesType, Double> PRICES = new HashMap<MoviesType, Double>();

  public static HashSet<String> HOLIDAYS = new HashSet<String>();

  public static String SYSTEM = new String();

  public static int numOfMovies = 0;

  public static int numOfShowtimes = 0;
  
  //Constructor that reads all the data file during initialisation of programme
  public Database(){
	  
      int i;
      for (FileType fileType : FileType.values()){
          if (readSerialisedObject(fileType) == null){
              System.out.println("Reading " + fileType + " failed!");
          }
      }
      
  }

  //function to determine the filetype
  public static String determineClass(Object object){
      Class c = object.getClass();
      String cStr = c.getName();
      cStr = cStr.substring(cStr.lastIndexOf('.')+1);
      return cStr;
  }

  //save a particular filetype into database
  public static void saveFile(Object object){
      serializeObject(object);

  }

  //save all files into the database
  public static void saveAllFiles(){
      for (FileType filetype : FileType.values()){
          serializeObject(filetype);
      }
  }

  //Serialise object into .dat files
  //need to change the dir
  public static void serializeObject(Object object){
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
  public static boolean initCineplex(){
      if (CINEPLEX.size() != 0){
          System.out.println("The database already has data!");
          return false;
      }
      CineplexManager.initCineplex();
      return true;
  }

  //Initialise movies
  public static boolean initMovies(){
      if (MOVIES.size() != 0){
          System.out.println("The databse already has data!");
          return false;
      }
      MovieManager.initMovies();
      numOfMovies = MOVIES.size();
      return true;
  }

  //Initialise showtime
  public static boolean initShowtime(){
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
  public static boolean resetDatabase(){
      USERS = new HashMap<String, User>();
      Database database = new Database();
      database.initStaff();
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
      database.initHoliday();
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
*/