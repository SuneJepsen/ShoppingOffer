package repository;

import java.util.List;

import domain.Coupon;

/**
 * Interface for coupons.
 */
public interface ICouponRepository {

    /**
     * Gets all coupons associated with a customer.
     *
     * @param customerId ID of the customer to get coupons for.
     *
     * @return Returns a list a of coupons.
     */
    List<Coupon> getCustomerCoupons(String customerId);
}
