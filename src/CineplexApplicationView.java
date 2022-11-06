package MobView;
import Helper.Helper;

import controller.*;

public class CineplexApplicationView implements ViewInterface {
	
	
	public CineplexApplicationView() {
        
        new MovieManager();
        new ShowtimeManager();
        new CineplexManager();
        new BookingManager();
        
		
	}
	
	public void printOptions() {
		System.out.println("Choose one of the following options");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println("3: Book movie as guest");
		System.out.println("4: Exit");
	}
	
	public void applicationView() {
		printOptions();
		
		int option;
		option = Helper.readInt(1,4);
		
		while(option != 4) {
			switch(option) {
				case 1:
					this.Login();
					break;
					
				case 2:
					this.register();
					break;
				
				
				case 3:
					MovieGoerView movieGoerView = new MovieGoerView("Cineplex App", "");
                    movieGoerView.applicationView();
					
				case 4:
					break;
			}
			printOptions();
			option = Helper.readInt(1,4);
		}
	}
	
	private void Login() {
		
		Helper.clearScreen();
		printPath p = new printPath();
		p.printRoute("Cineplex Application --> Login");
		
		System.out.println("Enter your username: ");
		String username = Helper.readString();

        System.out.println("\nPlease enter your password: ");
        String password = Helper.readString();
        System.out.println();
        
        String key = username.substring(0, 1);
        if (key.equals("$")) {
            if (UserManager.validateUser(username, password)) {
                System.out.println(
                        "Welcome staff " + username.substring(1) + " to MOBLIMA\n");
                Helper.pressAnyKeyToContinue();
                StaffView staffView = new StaffView("Cineplex Application");
                staffView.applicationView();
            } else {
                System.out.println("Invalid user!");
                Helper.pressAnyKeyToContinue();
            }
            
        } else {
            if (UserManager.validateUser(username, password)) {
                System.out.println("Welcome member " + username + " to MOBLIMA \n");
                Helper.pressAnyKeyToContinue();
                MovieGoerView movieGoerView = new MovieGoerView("Cineplex Application", username);
                movieGoerView.applicationView();
            } else {
                System.out.println("Invalid user!");
                Helper.pressAnyKeyToContinue();
            }
        }
       
        
	}

	
	private void register() {
		
    	Helper.clearScreen();
    	printPath p = new printPath();
    	p.printRoute("Cineplex Application --> Register");
    	System.out.println("Are you a customer or staff?");
    	System.out.println("1: Staff");
    	System.out.println("2: Customer");
    	
    	int choice;
    	choice = Helper.readInt();
    	Helper.clearScreen();
    	
    	if(choice == 1) {
    		p.printRoute("Cineplex Application --> Register --> Staff Register ");
    		System.out.println("Add $ to register for staff");
    		UserManager.signUp(1);
    	}
    	else {
    		p.printRoute("Cineplex Application --> Register --> Customer Register ");
    		UserManager.signUp(2);
    	}
        
        Helper.pressAnyKeyToContinue();

	}

}