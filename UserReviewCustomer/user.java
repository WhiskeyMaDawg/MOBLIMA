import java.util.Scanner;
class User extends Person{
  private String password;
  private userType userType;
  public void setPassword(){
    Scanner sc = new Scanner(System.in);
    System.out.print("New Password: ");
    String pw = sc.next();
    boolean valid = false;
    char c; int i;
    boolean hasLowerCase = false, hasUpperCase = false, hasNumber = false;
    while (!valid){
      //check length
      valid = true; //initialise as true before checking
      if (pw.length()<8) {
        valid = false;
        System.out.println("Password requires at least 8 characters");
      }
      //check for uppercase, lowercase, number
      for (i=0;i<pw.length();i++){
        c = pw.charAt(i);
        if (Character.isLowerCase(c))
          hasLowerCase = true;
        else if (Character.isUpperCase(c))
          hasUpperCase = true;
        else if (Character.isDigit(c))
          hasNumber = true;
      }
      if (!hasLowerCase){
        valid = false; 
        System.out.println("Password requires at least 1 lower case");
      }
      if (!hasUpperCase){
        valid = false; 
        System.out.println("Password requires at least 1 upper case");
      }
      if (!hasNumber){
        valid = false; 
        System.out.println("Password requires at least 1 number");
      }
      //check for special characters
      if (!(pw.contains("@") || pw.contains("#") || pw.contains("!") || pw.contains("~") || pw.contains("$") || pw.contains("%") || pw.contains("^") || pw.contains("&") || pw.contains("*") || pw.contains("(") || pw.contains(")") || pw.contains("-") || pw.contains("+") || pw.contains("/")|| pw.contains(":") || pw.contains(".") || pw.contains(", ") || pw.contains("<") || pw.contains(">") || pw.contains("?")|| pw.contains("|"))) {
        valid = false;
        System.out.println("Password requires at least 1 special character");
      }
      if (!valid){
        System.out.print("New password: ");
        pw = sc.next();
      }
    }
    this.password = pw;
  }
  public void setUserType(){
    Scanner sc = new Scanner();
    System.out.print("Are you a customer? ");
    String response = sc.nextLine();
    response=response.toLowerCase();
    if(response''==)
  }
  public userType getUserType(){
    return userType;
  }
}
