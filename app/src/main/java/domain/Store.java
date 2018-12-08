package domain;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public class Store {
    private int Id;
    private String Name;
    private Date CreatedDate;
    private ArrayList<Offer> Offers;
    private LatLng Location;

    public Store(int id, String name, Date createdDate, LatLng location, ArrayList<Offer> offers) {
        Id = id;
        Name = name;
        CreatedDate = createdDate;
        Offers = offers;
        Location = location;
    }
    public int getId(){ return Id; }
    public void setId(int id){ Id = id;}
    public String getName() { return Name; }
    public void setName(String name) { Name = name;}
    public Date getCreatedDate() { return CreatedDate; }
    public void setCreatedDate(Date createdDate) { CreatedDate = createdDate; }
    public ArrayList<Offer> getOffers() { return Offers; }
    public void setOffers(ArrayList<Offer> offers) { Offers = offers;}
    public LatLng getLocation() { return Location; }
    public void setLocation(LatLng location) { Location = location; }
}
