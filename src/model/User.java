package model;

import java.io.Serializable;

import Helper.Helper;

public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String userId;
    private String password;
    private boolean isStaff;

    public User(String userId, String username, String password, boolean isStaff) {
        setUserId(userId);
        setName(username);
        setPassword(password);
        setIsStaff(isStaff);
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsStaff() {
        return isStaff;
    }
}
