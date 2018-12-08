package domain;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * POJO of a Store. Contains information such as ID, created date and so on.
 */
public class Store {
    private int id;
    private String name;
    private Date createdDate;
    private ArrayList<Offer> offers;
    private LatLng location;

    public Store(int id, String name, Date createdDate, LatLng location, ArrayList<Offer> offers) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.offers = offers;
        this.location = location;
    }
    public int getId(){ return id; }
    public void setId(int id){ this.id = id;}
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public ArrayList<Offer> getOffers() { return offers; }
    public void setOffers(ArrayList<Offer> offers) { this.offers = offers;}
    public LatLng getLocation() { return location; }
    public void setLocation(LatLng location) { this.location = location; }
}
