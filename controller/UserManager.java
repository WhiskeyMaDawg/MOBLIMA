import Database.database;

public class UserManager {
    
    //Create a staff account
    public boolean createUser(String username, String password, String mobile, String email, AgeEnum age, boolean isStaff){
        //check with database
        //Code for checking database
        if (true){//database has the account
        System.out.println("User already exist!");
        return false;
        }

        int userId = 1; //Database + 1 (need change)
        String userIdStr = String.format("U%04d", userId);
        if (isStaff){
            User newUser = new Staff(userId, username, password, true);
        }
        else{
            User newUser = new MovieGoer(userId, username, mobile, email, age, password, false);
        }

        //move the new user details into the database
        //databse.USERS.put(username, newUser)
        //Database.saveFileIntoDatabase(FileType.USERS);
        System.out.println("Account created! Welcome to Cathay!");
        System.out.println("User details: ");
        displayUserDetails(newUser);
        return true;
    }

    //Validates the user authentication
    //Check the username first then check the password
    public boolean validateUser(String username, String password){
        if (database.USERS.containskey(username)){
            if (database.USERS.get(username).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //Initialising databse link with staff accounts
    public void initStaff(){
        createUser("$jiawen", "jiawen1", 89907655, "jiawen1@gmail.com", ageEnu.ADULT, true);
        createUser("$Nigel", "jiawen2", 89907656, "jiawen2@gmail.com", ageEnu.ADULT, true);
        createUser("$Dylan", "jiawen3", 89907657, "jiawen3@gmail.com", ageEnu.ADULT, true);
        createUser("$Sinpei", "jiawen4", 89907658, "jiawen4@gmail.com", ageEnu.ADULT, true);
        createUser("WanLoong", "jiawen5", 89907659, "jiawen5@gmail.com", ageEnu.ADULT, true);
    }

    //Print out any user details
    protected void printUser(User user){
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("UserID              : %s\n", user.getUserId());
        System.out.println("Username            : %s\n", user.getUsername());
        System.out.println("Password            : %s\n", user.getPassword());
        if (user.getIsStaff == false){
            MovieGoer customer = (MovieGoer) user;
            System.out.println("Mobile              : %s\n", user.getMobile());
            System.out.println("Email               : %s\n", user.getEmail());
            System.out.println("Age Group           : %s\n", user.getAgeGroup());
        }
        System.out.println("----------------------------------------");
        System.out.println();
    }

    //Print out all the staff details
    protected void printAllStaff(){
        for (User staff : database.USERS.values()){
            System.out.println();
            displayUserDetails(staff);
        }
    }

    //Sign up prompts
    public boolean signUp(int userType){
        System.out.println("Enter your username:");
        String username = Helper.readString();
        System.out.println("Enter your password:");
        String password = Helper.readString();

        if (userType == 1){
            System.out.println("Enter your phone number:");
            String mobile = Helper.readString();
            System.out.println("Enter your email:");
            String email = Helper.readString();
            System.out.println("Enter your age:");
            String age = Helper.readInt(1, 100);
            AgeEnum ageGroup;
            if(age > 0 && age <= 7){
                ageGroup = AgeEnum.CHILD;
            }
            else if(age > 7 && age <= 18){
                ageGroup = AgeEnum.STUDENT;
            }
            else if(age > 18 && age <= 55){
                ageGroup = AgeEnum.ADULT;
            }
            else{
                ageGroup = AgeEnum.ELDERLY;
            }
            return createUser(username, password, mobile, email, ageGroup, false)
        }

        else{
            return createUser(username, password, "", "", null, true);
        }
    }

    //retrieve user data from the database
    protected User getUser(String username){
        if (database.USERS.containskey(username)){
            return database.USERS.get(username);
        }
        return null;
    }

}
