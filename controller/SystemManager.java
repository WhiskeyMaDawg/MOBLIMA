package src.controller;

import java.util.ArrayList;
import java.util.Map;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Movie;
import src.model.User;
import src.model.enums.MoviesType;

public class SystemManager { //controller class for system configurations

	public static boolean addHoliday(String date) {//add date of holiday
	    Database.HOLIDAYS.add(date);
	    Database.saveFileIntoDatabase(FileType.HOLIDAYS);
	    System.out.println("Holiday on " + date + " added!");
	    return true; 
	}
	
	private static ArrayList<MoviesType> getTicketTypes() { //get ticket types based on MoviesType list
	    ArrayList<MoviesType> ticketTypes = new ArrayList<MoviesType>();
	    for (MoviesType type : Database.PRICES.keySet()) {
	    	ticketTypes.add(type);
	    }
	    return ticketTypes;
    	}
	
	public static boolean updateTicketPrices() { //set ticket price based on MoviesType
		displayTicketPricesStaff();
		ArrayList<MoviesType> ticketTypes = getTicketTypes();
		int choice;
		
		System.out.println("Select ticket price to update: ");
		for (int i = 1; i < (1+ticketTypes.size()); i++) {
			System.out.println("(" + (i) + ") " + ticketTypes.get(i-1));
		}
		System.out.println("(" + (1+ticketTypes.size()) + ") Exit");
		choice = Helper.readInt(1, ticketTypes.size() + 1);
		if (choice != (1+ticketTypes.size())) {
			MoviesType type = ticketTypes.get(choice - 1);
			System.out.println("\nEnter price to update to: ");
			double newPrice = Helper.readDouble(0, 100);
			Database.PRICES.put(type, newPrice);
			Database.saveFileIntoDatabase(FileType.PRICES);
			if (updateMoviePrices(type, newPrice)) {
				System.out.println("Ticket price successfully updated to $" + newPrice + " !");
			}
		}
		return true; //true when selected ticket price as been updated
	}
	
	private static boolean updateMoviePrices(MoviesType movieType, double price) { //update movie prices for the movie type based on updated ticket price
		for (Movie movie : Database.MOVIES.values()) {
			if (movie.getType() == movieType) {
				movie.setPrice(price);
			}
		}
		return true; //true when prices for the movie type has been updated
	}
	
	public static boolean validateStaff(String usernameEntered, String passwordEntered) { //staff authentication
		for (User staff : Database.USERS.values()) {
			if (staff.getName().equals(usernameEntered)) {
				if(staff.getPassword().equals(passwordEntered)) {
					return true; 
				}
			}
		}
		return false;
	}
  
	public static void initializeHolidays() { //initialise 10 random dates to be holidays in database
		String date;
		for (int i = 0; i < 10; i++) {
			date = Helper.generateRandomDate().substring(0, 10);
			addHoliday(date);
		}
	}

	protected static void displayTicketPricesStaff() { //display ticket prices for staff
		System.out.println("List of Current Ticket Prices");
		System.out.println(String.format("%-40s", "").replace(" ", "-"));
		for (Map.Entry<MoviesType, Double> type : Database.PRICES.entrySet()) {
			System.out.println(String.format("%-30s: %s", type.getKey(), Helper.df2.format(type.getValue())));
		}
		System.out.println(String.format("%-40s", "").replace(" ", "-"));
	}

	public static void displayTicketPrices() { //display ticket prices for movie goers
		  double price = Database.PRICES.get(MoviesType.TWO_D);
		  double weekdayDayPrice = price * 1.07;
		  double weekdayNightPrice = price * 1.15 * 1.07;
		  double weekendPrice = price * 1.2 * 1.07;
		  double holidayPrice = price * 1.25 * 1.07;
		  System.out.println("List of Normal Ticket Prices Inclusive of GST (Non-Blockbuster and Non-3D)");
		  System.out.println(String.format("%-40s", "").replace(" ", "-"));
		  System.out.println(String.format("%-30s: %s", "Weekdays before 7pm", "$" + Helper.df2.format(weekdayDayPrice)));
		  System.out.println(String.format("%-30s: %s", "Weekdays after 7pm", "$" + Helper.df2.format(weekdayNightPrice)));
		  System.out.println(String.format("%-30s: %s", "Weekends", "$" + Helper.df2.format(weekendPrice)));
		  System.out.println(String.format("%-30s: %s", "Public Holidays", "$" + Helper.df2.format(holidayPrice)));
		  System.out.println(String.format("%-40s", "").replace(" ", "-"));
	
		  System.out.println("\n*Note:");
		  System.out.println(String.format("%-40s", "").replace(" ", "-"));
		  System.out.println(String.format("%-30s: %s", "Child", "50% off all ticket prices"));
		  System.out.println(String.format("%-30s: %s", "Student & Senior Citizens", "25% off all ticket prices"));
		  System.out.println(String.format("%-30s: %s", "Platinum Cinemas", "Addtional charge of SGD5"));
		  System.out.println(String.format("%-40s", "").replace(" ", "-"));
	}
}
