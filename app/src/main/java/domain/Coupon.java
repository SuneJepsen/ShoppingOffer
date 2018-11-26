package domain;

import java.util.Date;

public class Coupon {
    private int Id;
    private int ConfirmationCode;
    private Date CreatedDate;
    private Boolean Sold;
    private Offer Offer;

    public Coupon(Date createdDate, domain.Offer offer) {
        CreatedDate = createdDate;
        Offer = offer;
    }
}
