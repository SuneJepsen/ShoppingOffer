package Repository;

import java.util.List;

import domain.Coupon;

public interface ICouponRepository {
    List<Coupon> GetUserCoupons(String userId);
}
