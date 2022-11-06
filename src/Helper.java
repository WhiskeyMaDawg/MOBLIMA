package Helper;

import java.text.*;
import java.lang.Exception;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

/**
 * Helper class to provide support functions for other classes
 * 
 */
public class Helper {
  /**
   * Scanner object for taking user input
   */
  public static final Scanner sc = new Scanner(System.in);

  /**
   * constructor for 2 decimal places
   */
  public static DecimalFormat df2 = new DecimalFormat("0.00");

  /**
   * constructor for 1 decimal place
   */
  public static DecimalFormat df1 = new DecimalFormat("0.0");

  /**
   * Default constructor for initializing Scanner object
   */
  public Helper() {
  }

  /**
   * Function to read an integer value from input
   * 
   * Function repeats until correct type (Integer) has been input
   * 
   * @return the desired value
   */
  public static int readInt() {
    while (true) {
      try {
        int input = Integer.MIN_VALUE;
        input = sc.nextInt();
        sc.nextLine(); 
        return input;
      } catch (InputMismatchException exception) {
        sc.nextLine();
        System.out.println("Input is not an integer!");
      }
    }
  }

  /**
   * Function to read integer from input with given limits
   * <p>
   * Function repeats until data of correct type and correct value has been entered
   * <p>
   * Repeats when input type differs from required type, throwing an {@link InputMismatchException}
   * {@link OutOfRange} is also thrown each time value is outside specified limits.
   * 
   * @param min minimum value to satisfies condition.
   * @param max maximum value which satisfies condition.
   * @return desired value.
   */
  public static int readInt(int min, int max) {
    while (true) {
      try {
        int input = Integer.MIN_VALUE;
        input = sc.nextInt();
        sc.nextLine();
        if (!(input>=min && input<=max)) {
          throw new OutOfRange();
        } else {
          return input;
        }
      } catch (InputMismatchException exception) {
        sc.nextLine();
        System.out.println("\nInput is not an integer!");
      } catch (OutOfRange e) {
        System.out.println("\nInput not within range!");
      }
    }
  }

  /**
   * @param message Message for confirmation prompt.
   * @return {true} if user input 'yes'. Otherwise, {false}.
   */
  public static boolean confirm(String text) {
    System.out.println(String.format("Would you like to %s? (Yes/No)", text));
    String input = sc.nextLine();
    input=input.toLowerCase();
    return input.equals("yes");
  }

  /**
   * Function to read double from input with given limits
   * <p>
   * Function repeats until data of correct type and correct value has been entered
   * <p>
   * Repeats when input type differs from required type, throwing an {@link InputMismatchException}
   * {@link OutOfRange} is also thrown each time value is outside specified limits.
   * 
   * @param min minimum value to satisfies condition.
   * @param max maximum value which satisfies condition.
   * @return desired value.
   */
  public static double readDouble(double min, double max) {
    while (true) {
      try {
        double input = -1;
        input = sc.nextDouble();
        sc.nextLine();
        if (!(input>=min && input<=max)) {
          throw new OutOfRange();
        } else {
          return input;
        }
      } catch (InputMismatchException exception) {
        sc.nextLine();
        System.out.println("\nInput is not a double!");
      } catch (OutOfRange exception) {
        System.out.println("\nInput not within range");
      }
    }
  }

  /**
   * Reads string
   * @return input as string
   */
  public static String readString() {
    return sc.nextLine();
  }


  /**
   * Method to generate unique id for hashMap key
   * 
   * @param <K>      Generic type for the key of the HashMap
   * @param <V>      Generic type for the value of the HashMap
   * @param database Hashmap object to reference
   * @return A unique id for the database
   */
  public static <K, V> int generateUniqueId(HashMap<K, V> db) {
    if (db.size() == 0) {
      return 1;
    }
    String max = new String();
    for (K key : db.keySet()) {
      if (key instanceof String) {
        String curr = (String) key;
        if (curr.compareTo(max) > 0) {
          max = curr;
        }
      }
    }
    String Id = max.substring(1);
    return Integer.parseInt(Id) + 1;
  }

  /**
   * Method to set the date for either current date or user input date
   * 
   * @param now       {@code true} to return the current time. Otherwise,
   *                  {@code false}
   *                  to prompt user for new time.
   * @param isHoliday boolean {@code true} if the date is a holiday, {@code false}
   *                  otherwise
   * 
   * @return String object for the date in the format "yyyy-MM-dd HH:mm" or
   *         "yyyy-MM-dd"
   */
  public static String setDate(boolean now, boolean isHoliday) {
    if (now) {
      return getTimeNow();
    }

    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    String dateInput = "";
    String timeInput = "00:00";
    System.out.println("Please input a date in the format: yyyy-MM-dd");
    dateInput = sc.next();
    sc.nextLine();

    if (!isHoliday) {
      System.out.println("Please input a time in the format: HH:mm");
      timeInput = sc.next();
      sc.nextLine();
    }
    String date = dateInput + " " + timeInput;

    try {
      LocalDateTime Date = LocalDateTime.parse(date, f);
      date = f.format(Date);
      if (validateDate(date, f)) {
        if (isHoliday) {
          return date.substring(0, 10);
        }
        return date;
      } else {
        System.out.println("\nDate does not exist");
      }
    } catch (DateTimeParseException exception) {
      System.out.println("\nInvalid Date format");
    }
    return "";
  }

  /**
   * Method to parse a string date in a format
   * 
   * @param date   Date in string
   * @param format {@link DateTimeFormatter} object for formatting of dates
   * @return {@link LocalDateTime} object after parsing the string date with the
   *         formatter
   */
  public static LocalDateTime getDate(String date, DateTimeFormatter f) {
    return LocalDateTime.parse(date, f);
  }

  /**
   * Method to get current date and time
   * 
   * @return String object for the date in the format "yyyy-MM-dd HH:mm"
   */
  public static String getTimeNow() {
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.now().format(f);
  }

  /**
   * Date validator
   * 
   * @param date   Date in string
   * @param format {@link DateTimeFormatter} object for formatting of dates
   * @return {@code true} if date is valid. Otherwise, {@code false} if the date
   *         is invalid (date is in the past)
   */
  public static boolean validateDate(String date, DateTimeFormatter format) {
    LocalDateTime Date = getDate(date, format);
    if(Date.compareTo(LocalDateTime.now())>=0)return true;
    else return false;
  }
  /*
   * Method to check if the time difference of the input date and current time
   * exceeds 1 hour (Hotel check in / check out checking)
   * 
   * @param date Date in string
   * @return {@code true} if the date does not exceed 1 hour. Otherwise,
   *         {@code false}.
  public static boolean localDateTimediff(String date) {
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime from = getDate(date, f);
    LocalDateTime to = LocalDateTime.now();
    LocalDateTime fromTemp = LocalDateTime.from(from);
    long h = fromTemp.until(to, ChronoUnit.HOURS);
    fromTemp = fromTemp.plusHours(h);
    //long m = fromTemp.until(to, ChronoUnit.MINUTES);
    //fromTemp = fromTemp.plusMinutes(m);
    if (h<=1)
      return false;
    return true;
  }
  */
  /**
   * Calculate the number of days between two dates
   * 
   * @param fromDate From date in string.
   * @param toDate   To date in string.
   * @return Days difference of the two dates.
   */
  public static long calculateDaysElapsed(String fromDate, String toDate) {
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime from = getDate(fromDate, f);
    LocalDateTime to = getDate(toDate, f);
    long daysBetween = from.until(to, ChronoUnit.DAYS);
    return daysBetween + 1;
  }

  /**
   * Checks whether dates are chronological
   * 
   * @param fromDate From date in string.
   * @param toDate   To date in string.
   * @return {@code true} if the fromDate precedes toDate. Otherwise,
   *         {@code false}
   */
  public static boolean validateTwoDates(String fromDate, String toDate) {
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime from = getDate(fromDate, f);
    LocalDateTime to = getDate(toDate, f);
    return (to.compareTo(from) >= 0);
  }

  /**
   * Checks whether date is a Saturday or Sunday
   * @param dateToCheck Date to check in String
   * @return {@code true} if Saturday or Sunday. Otherwise,
   *         {@code false}.
   */
  public static boolean checkIsDateWeekend(String dateToCheck) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = getDate(dateToCheck, format);
    DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
      return true;
    }
    return false;
  }

  /**
   * Method to check if the time is at night
   * 
   * @param dateToCheck To check the given hours for given date
   * @return {@code true} if the time is at night. Otherwise,
   *         {@code false}.
   */
  public static boolean checkIsTimeNight(String dateToCheck) {
    System.out.println(dateToCheck.substring(11, 13));
    int time = Integer.parseInt(dateToCheck.substring(11, 13));
    if (time >= 9 && time <= 18) {
      return false;
    }
    return true;
  }

  /**
   * Method to pause the application and prompt user to press the ENTER key to
   * continue using the app.
   */
  public static void pressAnyKeyToContinue() {
    System.out.println("Press <Enter> key to continue...");
    try {
      System.in.read();
    } catch (Exception e) {
    }
  }

  /**
   * Clear the screen of the terminal for user experience and neat
   * interface.
   */
  public static void clearScreen() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception err) {
    }
  }

  /**
   * Generate random date and hour
   * @return generated value in String type
   */
  public static String generateRandomDate() {
    Random random = new Random();
    int minDay = (int) LocalDate.of(2022, 11, 1).toEpochDay();
    int maxDay = (int) LocalDate.of(2023, 1, 1).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    ZoneId defaultZoneId = ZoneId.systemDefault();

    randomDate = LocalDate.ofEpochDay(randomDay);
    Date date = Date.from(randomDate.atTime(getRandomNumber(0, 24), 0).atZone(defaultZoneId).toInstant());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return dateFormat.format(date);
  }

  /**
   * Checks whether string is numerical
   * @param strNum input
   * @return true is the string is Numeric; else false
   */
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static int getRandomNumber(int min, int max) {
    Random rand = new Random();
    return max - rand.nextInt(max - min);
  }
}
