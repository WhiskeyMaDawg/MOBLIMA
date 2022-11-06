package controller;

import database.Database;


import database.FileType;
import Helper.Helper;
import model.Movie;
import model.MovieGoer;
import model.Staff;
import model.User;
import model.enums.AgeEnum;

public class UserManager {
    
    //Create a staff account
    public static boolean createUser(String username, String password, String mobile, String email, AgeEnum age, boolean isStaff){
        //check with database
        //Code for checking database
        if (Database.USERS.containsKey(username)){//database has the account
        	System.out.println("User already exist!");
        return false;
        }

        int userId = Database.USERS.size() + 1; //Database + 1 (need change)
        String userIdStr = String.format("U%04d", userId);
        User newUser;
        
        if (isStaff){
            newUser = new Staff(userIdStr, username, password, true);
        }
        else{
            newUser = new MovieGoer(userIdStr, username, mobile, email, age, password, false);
        }

        //move the new user details into the database
        Database.USERS.put(username, newUser);
        Database.saveFile(FileType.USERS);
        System.out.println("Account created! Welcome to Cathay!");
        System.out.println("User details: ");
        printUser(newUser);
        return true;
    }

    //Validates the user authentication
    //Check the username first then check the password
    public static boolean validateUser(String username, String password){
        if (Database.USERS.containsKey(username)){
            if (Database.USERS.get(username).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //Initialising databse link with staff accounts
    public static void initStaff(){
        createUser("$jiawen", "jiawen1", "89907655", "jiawen1@gmail.com", AgeEnum.ADULT, true);
        createUser("$Nigel", "jiawen2", "89907656", "jiawen2@gmail.com", AgeEnum.ADULT, true);
        createUser("$Dylan", "jiawen3", "89907657", "jiawen3@gmail.com", AgeEnum.ADULT, true);
        createUser("$Sinpei", "jiawen4", "89907658", "jiawen4@gmail.com", AgeEnum.ADULT, true);
        createUser("WanLoong", "jiawen5", "89907659", "jiawen5@gmail.com", AgeEnum.ADULT, true);
    }

    //Print out any user details
    protected static void printUser(User user){
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.printf("UserID              : %s\n" , user.getUserId());
        System.out.printf("Username            : %s\n" , user.getName());
        System.out.printf("Password            : %s\n" , user.getPassword());
        if (user.getIsStaff() == false){
            MovieGoer customer = (MovieGoer) user;
            System.out.printf("Mobile              : %s\n", customer.getMobile());
            System.out.printf("Email               : %s\n", customer.getEmail());
            System.out.printf("Age Group           : %s\n", customer.getAgeGroup());
        }
        System.out.println("----------------------------------------");
        System.out.println();
    }

    //Print out all the staff details
    protected void printAllStaff(){
        for (User staff : Database.USERS.values()){
            System.out.println();
            printUser(staff);
        }
    }

    //Sign up prompts
    public static boolean signUp(int userType){
        System.out.println("Enter your username:");
        String username = Helper.readString();
        System.out.println("Enter your password:");
        String password = Helper.readString();

        if (userType == 2){
            System.out.println("Enter your phone number:");
            String mobile = Helper.readString();
            System.out.println("Enter your email:");
            String email = Helper.readString();
            System.out.println("Enter your age:");
            int age = Helper.readInt(1, 100);
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
                ageGroup = AgeEnum.SENIOR_CITIZEN;
            }
            return createUser(username, password, mobile, email, ageGroup, false);
        }

        else{
            return createUser(username, password, "", "", null, true);
        }
    }

    //retrieve user data from the database
    protected static User getUser(String username){
        if (Database.USERS.containsKey(username)){
            return Database.USERS.get(username);
        }
        return null;
    }

}
