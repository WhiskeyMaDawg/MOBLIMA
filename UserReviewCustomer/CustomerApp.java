import java.util.Scanner;

public class CustomerApp {
	public static void main(String[] args) {
		//public Customer(int age, Age ageEnum, int mobileNumber, String movieHistory)
		
		int age;
		int mobileNumber;
		String email;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your age: ");
		age = sc.nextInt();
		System.out.println("Enter your mobile number: ");
		mobileNumber = sc.nextInt();
		System.out.println("Enter your email: ");
		email = sc.next();
		Customer test = new Customer(age, Age.CHILD, mobileNumber, email);
		test.setAgeEnum(age);
		System.out.println("Customer age cat: " + test.getAgeEnum());
		System.out.println("Customer age: " + test.getAge());
		System.out.println("Customer mobile number: " + test.getMobileNumber());
		System.out.println("Customer email: " + test.getEmailAddress());
		
		
		
		
	}
}
