package session;

import java.util.List;

import domain.Coupon;
import domain.Offer;

/**
 * Interface for sessions. Used for saving offers in memory.
 */
public interface ISessionRepository {

    /**
     * Saves offer that users have reserved. Binds the offer to the user.
     *
     * @param customerId ID of the user to attach the offer to.
     * @param offer The offer to be attached, using the offer ID.
     */
    void saveOfferToCustomer(String customerId, Offer offer);

    /**
     * Get all coupons associated with a user.
     *
     * @param customerId The ID of the user to use when getting coupons.
     *
     * @return Returns the coupons associated with a user.
     */
    List<Coupon> getCustomerCoupons(String customerId);
}
