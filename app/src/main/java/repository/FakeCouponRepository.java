package repository;

import java.util.List;
import domain.Coupon;
import session.ISessionRepository;


public class FakeCouponRepository implements  ICouponRepository{

    private final ISessionRepository prefRepo;


    public FakeCouponRepository(ISessionRepository prefRepo) {
        this.prefRepo = prefRepo;
    }

    @Override
    public List<Coupon> GetUserCoupons(String userId) {
        return prefRepo.GetUserCoupons(userId);
    }
}
