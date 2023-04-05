package ca.tabletop;

public class Quest 
{
    public Quest(String title, String details) {
        this.title = title;
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

    public String toString()
    {
        String string = title + " | " + details;
        return string;
    }
}
