package src.model;

/**
 * The class that represents a staff in the MOBLIMA system

 */
public class Staff extends User {
    /**
     * Staff constructor with @param userId @param username @param password of staff @param isStaff  whether user is staff
     */
    public Staff(String userId, String username, String password, boolean isStaff) {
        super(userId, username, password, isStaff);
    }
}
