package domain;

import java.util.ArrayList;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public class User {
    private String UserId;
    private ArrayList<Coupon> Coupons;
    public User(String userId) {
        UserId = userId;
        Coupons = new ArrayList<Coupon>();
    }

    public void addCoupon(Coupon coupon) {
        this.Coupons.add(coupon);
    }
}
