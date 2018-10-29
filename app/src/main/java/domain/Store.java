package domain;

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

    public Store(int id, String name, Date createdDate, ArrayList<Offer> offers) {
        Id = id;
        Name = name;
        CreatedDate = createdDate;
        Offers = offers;
    }
    public int getId(){ return Id; }
    public void setId(int id){ Id = id;}
    public String getName() { return Name; }
    public void setName(String name) { Name = name;}
    public Date getCreatedDate() { return CreatedDate; }
    public void setCreatedDate(Date createdDate) { CreatedDate = createdDate; }
    public ArrayList<Offer> getOffers() { return Offers; }
    public void setOffers(ArrayList<Offer> offers) { Offers = offers;}
}
