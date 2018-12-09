package repository;

import java.util.ArrayList;

import domain.Offer;

/**
 * Interface for offers.
 */
public interface IOfferRepository {

    /**
     * Used for getting an arbitary offer using the offer ID.
     *
     * @param id The ID of the offer.
     *
     * @return Returns the requested Offer.
     */
    Offer getOfferById(int id);

    /**
     * Used for getting offers by ID
     *
     * @return Returns the requested Offers.
     */
    ArrayList<Offer> getOfferByIds(ArrayList<Integer>offerIds);

    /**
     * Get offers associated with a store, using the store ID.
     *
     * @param storeId ID of the store to use to get the Offers.
     *
     * @return Returns all the offers associated with a store.
     */
    ArrayList<Offer> getStoreOffers(Integer storeId);

    /**
     * Saves offer that users have reserved. Binds the offer to the user.
     *
     * @param customerId ID of the user to attach the offer to.
     * @param offerId The offer to be attached, using the offer ID.
     */
    void saveOfferToCustomer(String customerId, int offerId);
}
