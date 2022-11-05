import java.util.Scanner;
public class userApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your first name: ");
		String firstName=sc.nextLine();
		System.out.println("Please enter your last name: ");
		String lastName=sc.nextLine();
		User test = new User(firstName,lastName);
		test.setUserType();	
	}
}
