package domain;

import java.util.Date;

/**
 * POJO of a coupon. Contains information such as ID, created date and so on.
 */
public class Coupon {
    private int id;
    private int confirmationCode;
    private Date createdDate;
    private Boolean sold;
    private Offer offer;

    public Coupon(int id, Date createdDate, domain.Offer offer) {
        this.createdDate = createdDate;
        this.offer = offer;
        this.id = id;
    }

    public domain.Offer getOffer() {
        return offer;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedDate() { return createdDate; }
}
