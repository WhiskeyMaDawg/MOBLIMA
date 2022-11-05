package src.model;

import src.model.enums.AgeGroup;

/**
 * MovieGoer Class
 */
public class MovieGoer extends User {
    /**
     * MovieGoer phone number
     */
    private String mobile;

    /**
     * MovieGoer email address
     */
    private String email;

    /**
     * Age group of the movie goer
     */
    private AgeGroup ageGroup;

    /**
     * Movie goer constructor using @param userId  @param username @param mobile @param email  @param ageGroup {@link AgeGroup} of movie goer
     * @param password @param isStaff
     */
    public MovieGoer(String userId, String username, String mobile, String email, AgeGroup ageGroup, String password,
            boolean isStaff) {
        super(userId, username, password, isStaff);
        setMobile(mobile);
        setEmail(email);
        setAgeGroup(ageGroup);
    }
    
    /**
     * Sets the {@link AgeGroup} of the movie goer using @param ageGroup
     */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     * Gets and @return ageGroup of the movie goer
     */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
     * Sets the mobile number of the movie goer using @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets and @return mobile number of the movie goer
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the email address of the movie goer using @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets and @return email address of the movie goer
     */
    public String getEmail() {
        return email;
    }

    
}
