package repository;

import java.util.List;
import domain.Coupon;
import session.ISessionRepository;

/**
 * Fake repository for coupons. Will be replaced by actual data storage.
 */
public class FakeCouponRepository implements  ICouponRepository{

    private final ISessionRepository prefRepo;

    public FakeCouponRepository(ISessionRepository prefRepo) {
        this.prefRepo = prefRepo;
    }

    @Override
    public List<Coupon> getCustomerCoupons(String customerId) {
        return prefRepo.getCustomerCoupons(customerId);
    }
}
