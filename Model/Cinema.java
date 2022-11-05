package src.model;

import java.io.Serializable;

import src.model.enums.Location;

public class Cinema implements Serializable { //initialise Cinema
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 2L;
    private Location location; //cineplex of cinema
    private String cinemaCode; //unique code for cinema
    private boolean isPlatinum; //true if cinema is of Platinum type

    /**
     * Constructor of Cinema
     * 
     * @param location   of the Cinema
     * @param cinemaCode of the Cinema
     * @param isPlatinum (type) of Cinema
     */
    public Cinema(Location location, String cinemaCode, boolean isPlatinum) {
        this.location = location;
        this.cinemaCode = cinemaCode;
        this.isPlatinum = isPlatinum;
    }

    public void setCinemaCode(String cinemaCode) { //set unique code for cinema
        this.cinemaCode = cinemaCode;
    }

    public void setIsPlatinum(boolean isPlatinum) { //set type of cinema
        this.isPlatinum = isPlatinum;
    }

    public void setCineplex(Location location) { //set cineplex of cinema
        this.location = location;
    }

    public Location getCineplex() { //get cineplex of cinema
        return location;
    }

    public String getCinemaCode() { //get unique code of cinema
        return cinemaCode;
    }

    public boolean getIsPlatinum() { //get type of cinema
        return isPlatinum;
    }
}
