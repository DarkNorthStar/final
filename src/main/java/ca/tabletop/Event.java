package ca.tabletop;

public class Event 
{
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
