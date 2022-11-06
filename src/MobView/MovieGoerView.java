package MobView;
import java.util.Scanner;

public class MovieGoerView implements ViewInterface {
	
    private String path;
    private String username;
    
    public MovieGoerView(String path, String username) {
        super();
        this.path = path;
        this.username = username;
    }
    
	public void printOptions() {
		System.out.println("Choose one of the following options");
		System.out.println("1: View cineplexes");
		System.out.println("2: View available movies");
		System.out.println("3: View ticket prices");
		System.out.println("4: View booking history");
		System.out.println("5: Exit");
	}
	
	public void applicationView() {
		printOptions();
		
		int option;
		//option = Helper.readInt(1,3);
		Scanner sc = new Scanner(System.in);
		option = sc.nextInt();
		
		while(option!=5) {
			switch(option) {
				
			}
		}
	}
}
