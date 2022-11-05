import java.util.Scanner;
class User extends Person{
  public User(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}
  private String password;
  private userType userType;
  /*public void setPassword(){
    Scanner sc = new Scanner(System.in);
    System.out.print("New Password: ");
    String pw = sc.nextLine();
    boolean valid = false;
    while (!valid){
      
    }
    sc.close();
  }*/
  public void setUserType(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Are you a customer? ");
    String response = sc.nextLine();
    response=response.toLowerCase();
    if(response.equals("yes")) {this.userType=userType.CUSTOMER;System.out.println("Welcome to our booking system!");}
    else this.userType=userType.STAFF;
    sc.close();
  }
  public userType getUserType(){
    return userType;
  }
}