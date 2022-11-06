package model;

import java.io.Serializable;

import java.util.ArrayList;

import model.enums.Location;

public class Cineplex implements Serializable { //initialise cineplex
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 4L;
    private String cineplexID; //id of cineplex
    private Location location; //location of Cineplex
    private int numOfCinemas; //number of cinema in the cineplex
    private ArrayList<Cinema> cinemaList; //list of cinemas in the cineplex

    /**
     * Constructor of Cineplex
     * 
     * @param cineplexId of cineplex
     * @param location of Cineplex
     */
    public Cineplex(String cineplexId, Location location) {
        setCineplexId(cineplexId);
        this.location = location;
        this.numOfCinemas = 10;
        this.cineplexID = cineplexId;
        initCineplex();
    }

    private void initCineplex() { //initialise cineplex
        String cinemaCode = location.getLabel().substring(0, 2).toUpperCase();
        this.cinemaList = new ArrayList<Cinema>();
        for (int i = 0; i < this.numOfCinemas; i++) {
            String s = "" + i;
            Cinema newCinema = new Cinema(this.getLocation(), cinemaCode + s, i < 4 ? true : false);
            this.cinemaList.add(newCinema);
        }
    }

    public void setCineplexId(String newID) { //set ID of cineplex
        this.cineplexID = newID;
    }

    public void setNumOfCinemas(int newNum) { //set number of cinemas in the cineplex
        this.numOfCinemas = newNum;
    }

    public void setCinemaList(ArrayList<Cinema> newList) { //set the list of cinemas in the cineplex
        this.cinemaList = newList;
    }

    public String getCineplexId() { //get id of cineplex
        return this.cineplexID;
    }

    public Location getLocation() { //get location of cineplex
        return this.location;
    }

    public String getLocationStr() { //get location of cineplex in string format
        return this.location.getLabel();
    }

    public int getNumOfCinemas() { //get number of cinemas in the cineplex
        return this.numOfCinemas;
    }
    
    public ArrayList<Cinema> getCinemaList() { //get the list of cinemas in the cineplex
        return this.cinemaList;
    }
}
