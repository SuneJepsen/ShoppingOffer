package domain;

import java.util.Date;

public class Coupon {
    private int Id;
    private int ConfirmationCode;
    private Date CreatedDate;
    private Boolean Sold;
    private Offer Offer;

    public Coupon(int id, Date createdDate, domain.Offer offer) {
        CreatedDate = createdDate;
        Offer = offer;
        Id = id;
    }

    public domain.Offer getOffer() {
        return Offer;
    }

    public int getId() {
        return Id;
    }

    public Date getCreatedDat() { return CreatedDate; }
}
