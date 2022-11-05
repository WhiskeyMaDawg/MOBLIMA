package src.database;

/**
 * Enumeration for the different file types
 */ 
public enum FileType {
  /**
   * Relevant file type corresponding to the {@link Staff} file.
   */
  USERS("Users"),

  /**
   * Relevant file type corresponding to the {@link Booking} file.
   */
  BOOKINGS("Booking"),

  /**
   * Relevant file type corresponding to the {@link Cineplex} file.
   */
  CINEPLEX("Cineplex"),

  /**
   * Relevant file type corresponding to the {@link Showtime} file.
   */
  SHOWTIME("Showtime"),

  /**
   * Relevant file type corresponding to the {@link Movie} file.
   */
  MOVIES("Movies"),

  /**
   * Relevant file type corresponding to the movie prices file.
   */
  PRICES("Prices"),

  /**
   * Relevant file type corresponding to the Holidays file.
   */
  HOLIDAYS("Holidays"),

  /**
   * Relevant file type corresponding to the System file.
   */
  SYSTEM("System");

  /**
   * File type string value
   */
  public final String fileName;

  /**
   * FileType constructor using @param fileName 
   */
  private FileType(String fileName) {
    this.fileName = fileName;
  }
}
