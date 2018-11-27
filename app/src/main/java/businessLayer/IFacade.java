package businessLayer;

import java.util.ArrayList;
import java.util.List;

import domain.Coupon;
import domain.Offer;
import domain.Store;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public interface IFacade {
    Offer getOfferById(int id);
    ArrayList<Offer> getOffersByLatLong(double latitude, double longitude);
    ArrayList<Offer> getStoreOffers(Integer storeId);
    ArrayList<Store> getStores(double latitude, double longitude);
    void SaveOfferToUser(String userId, int offerId);
    List<Coupon> GetUserCoupons(String userId);
}
