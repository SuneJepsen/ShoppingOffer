package repository;

import java.util.ArrayList;

import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public interface IOfferRepository {
    Offer getOfferById(int id);
    ArrayList<Offer> getOfferByIds(ArrayList<Integer>offerIds);
    ArrayList<Offer> getStoreOffers(Integer storeId);
    void saveOfferToCustomer(String userId, int offerId);
}
