package Repository;

import java.util.List;

import domain.Coupon;
import domain.Offer;

public interface ISessionRepository {
    void SaveOfferToUser(String userId, Offer offer);
    List<Coupon> GetUserCoupons(String userId);
}
