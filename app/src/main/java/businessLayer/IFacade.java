package businessLayer;

import java.util.ArrayList;
import java.util.List;

import domain.Coupon;
import domain.Offer;
import domain.Store;

/**
 * Interface that has several method signatures.
 */
public interface IFacade {
    /**
     * Used for getting an arbitary offer using the offer ID.
     *
     * @param id The ID of the offer.
     *
     * @return Returns the requested Offer.
     */
    Offer getOfferById(int id);

    /**
     * Used for getting offers by where the offer was created using [latitude] and [longitude].
     *
     * @return Returns the requested Offer.
     */
    ArrayList<Offer> getOffersByLatLong(double latitude, double longitude);

    /**
     * Get offers associated with a store, using the store ID.
     *
     * @param storeId ID of the store to use to get the Offers.
     *
     * @return Returns all the offers associated with a store.
     */
    ArrayList<Offer> getStoreOffers(Integer storeId);

    /**
     * Used for getting stores by where the store geofence was created using [latitude] and [longitude].
     *
     * @return Returns all stores on a given latitude and logitude.
     */
    ArrayList<Store> getStores(double latitude, double longitude);

    /**
     * Saves offer that users have reserved. Binds the offer to the user.
     *
     * @param userId ID of the user to attach the offer to.
     * @param offerId The offer to be attached, using the offer ID.
     */
    void saveOfferToUser(String userId, int offerId);

    /**
     * Get all coupons associated with a user.
     *
     * @param userId The ID of the user to use when getting coupons.
     *
     * @return Returns the coupons associated with a user.
     */
    List<Coupon> getUserCoupons(String userId);

    /**
     * Get individual stores using the store ID.
     *
     * @param storeId The ID of the store to return
     *
     * @return Returns a store associated with the ID.
     */
    Store getStoreById(int storeId);
}
