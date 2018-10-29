package Repository;

import java.util.ArrayList;

import domain.Offer;
import domain.Store;

/**
 * Created by Sune Jepsen on 29-10-2018.
 */

public interface IStoreFactory {
    ArrayList<Store> getStores();
    ArrayList<Offer> getOffers();
}

