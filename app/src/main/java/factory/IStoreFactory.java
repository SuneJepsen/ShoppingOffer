package factory;

import java.util.ArrayList;

import domain.Offer;
import domain.Store;

/**
 * Interface for  the factory.
 */
public interface IStoreFactory {
    /**
     * Gets all stores.
     *
     * @return Returns list of stores.
     */
    ArrayList<Store> getStores();

    /**
     * Gets all offers.
     *
     * @return Returns list of offers.
     */
    ArrayList<Offer> getOffers();
}

