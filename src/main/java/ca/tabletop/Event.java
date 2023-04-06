/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   Event.java
 *  Description:
 *              This is the events class.
 *              The events class is currently being used to create events objects that are pushed to the database.
 *              To make events and quests more diffrent from eachother, Events will be removed from the database and added to a log file.
 */
package ca.tabletop;

public class Event 
{
    // Constructor for events details
    public Event(String details) {
        this.details = details;
    }
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private String details;
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}
