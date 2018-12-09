package domain;

import java.util.ArrayList;

/**
 * POJO of a Customer. Contains information such as customer ID and so on.
 */
public class Customer {
    private String customerId;
    private ArrayList<Coupon> coupons;

    public Customer(String customerId) {
        this.customerId = customerId;
        coupons = new ArrayList<Coupon>();
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }
}
