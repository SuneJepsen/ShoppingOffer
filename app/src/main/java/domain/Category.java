package domain;

import java.util.Date;

/**
 * POJO of a Category. Contains information such as ID, created date and so on.
 *
 * This class is not used yet.
 */
public class Category{

    private int id;
    private String title;
    private Date createdDate;

    public Category(int id, String title, Date createdDate) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}