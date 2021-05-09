// -----------------------------------------------------
// Part: 2
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package TvGuide;

import java.util.ArrayList;
/**
 * TV Show class, which contains information on a given Tv show, as well as some
 * basic methods to handle the data.
 * @author Kevin Marnet Scanlan
 */
public class TVshow implements Watchable, Cloneable{
    private String showID;
    private static ArrayList<String> showIDs = new ArrayList<>();
    private String showName;
    private double startTime;
    private double endTime;

    /**
     * Parameterized constructor, takes an input for each non-static data field,
     * and then sets it as a new TV show.
     * @param showID The show ID
     * @param showName The show name
     * @param startTime The start time
     * @param endTime The end time
     */
    public TVshow(String showID, String showName, double startTime, double endTime) {
        //makes sure ID is unique
        while(!TVshow.isIDUnique(showID)) {
                showID += "*";
            }
        this.showID = showID;
        showIDs.add(showID);
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /** 
     * Copy constructor, takes a TV show and a ID.
     * @param show A TV show
     * @param showID A Show ID
     */
    public TVshow(TVshow show, String showID) {
        //makes sure ID is unique
            while(!TVshow.isIDUnique(showID)) {
                showID += "*";
            }
        this.showID = showID;
        showIDs.add(showID);
        this.showName = show.showName;
        this.startTime = show.startTime;
        this.endTime = show.endTime;
    }
    
    /**
     * Clone method for a TV Show.
     * @param showID An ID for the TV show.
     * @return The cloned TV show (except for the ID)
     */
    public TVshow clone(String showID) {
        return new TVshow(this, showID);
    }

    /**
     * Getter for the show ID
     * @return the Show ID
     */
    public String getShowID() {
        return showID;
    }

    /**
     * Getter for the list of Show IDs
     * @return The list of Show IDs
     */
    public static ArrayList<String> getShowIDs() {
        return showIDs;
    }

    /**
     * Getter for the show name
     * @return the show name
     */
    public String getShowName() {
        return showName;
    }

    /**
     * Setter for the show name
     * @param showName the new show name
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * Getter for the start time
     * @return the start time
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time 
     * @param startTime the new start time
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the end time
     * @return the end time
     */
    public double getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time
     * @param endTime the new end time
     */
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
    
    /**
     * Method that checks if a show ID is unique.
     * @param showID The ID to be checked
     * @return true if the ID is unique, false if it is already in the ID list
     */
    public static boolean isIDUnique(String showID) {
        return !showIDs.contains(showID);
    }
    
    /**
     * ToString method
     * @return String version of a TVshow
     */
    @Override
    public String toString() {
        return "TVshow{ID: " + showID + ", Name of show: " + showName + ", Start time: " + startTime + ", End time: " + endTime + '}';
    }

    /**
     * Equals method, that compares 2 TV show objects by their parameters (except for the ID)
     * @param obj The Tv show being compared to
     * @return Whether they are equivalent TV shows or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TVshow other = (TVshow) obj;
        if (this.startTime != other.startTime) {
            return false;
        }
        if (this.endTime != other.endTime) {
            return false;
        }
        if (!this.showName.equals(other.showName)) {
            return false;
        }
        return true;
    }
    
     /**
      * Method that determines whether 2 shows run-time overlaps, starts at the same
      * time, or is at completely different times.
      * @param s The show being compared to
      * @return short string answer of the result of the time frame comparison
      */
    @Override
    public String isOnSameTime(TVshow s) {
        if(this.startTime == s.startTime)
            return "Same time";
        
        if((this.startTime <  s.startTime && this.endTime > s.startTime) || 
                (this.startTime > s.startTime && this.startTime < s.endTime))
            return "Some overlap";
        
        return "Different time";
    }
}
