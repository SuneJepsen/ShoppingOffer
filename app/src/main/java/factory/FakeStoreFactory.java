package factory;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

import domain.Offer;
import domain.Store;

/**
 * Used for creating fake offers and stores. This will be replaced by an actual data storage solution.
 */
public class FakeStoreFactory implements IStoreFactory {
    private final ArrayList<Store> stores;

    public FakeStoreFactory() {
        this.stores = new ArrayList<Store>(){{
            add(new Store(1, "Only", new Date(), new LatLng(55.367913, 10.428155), new ArrayList<Offer>(){{
                add(new Offer( 23, "Sweater", 30, 55.367913,10.428155, 499.99, "p1", new Date(), 50,  new Date(), 1));
                add(new Offer( 24, "Shirt", 60, 55.367913,10.428155, 199.99, "shirt", new Date(), 20,  new Date(), 1 ));
            }}));

            add(new Store(2, "Vera Moda", new Date(), new LatLng(55.367913, 10.428155), new ArrayList<Offer>(){{
                add(new Offer( 25, "Pants", 60, 55.367913,10.428155, 299.99, "pants", new Date(), 100,  new Date(), 2));
                add(new Offer( 26, "Shirt", 40, 55.367913,10.428155, 249.99, "shirt", new Date(), 120,  new Date(), 2 ));
                add(new Offer( 27, "Jackets", 80, 55.367913,10.428155, 399, "p9", new Date(), 100,  new Date(),2 ));
            }}));

            add(new Store(3, "Companys", new Date(), new LatLng(55.3957872, 10.372331), new ArrayList<Offer>(){{
                add(new Offer( 28, "Shoes", 90, 55.367913,10.428155, 99.99, "p2", new Date(), 80,  new Date(), 3));
                add(new Offer( 29, "Shoes and Shirt", 50, 55.367913,10.428155, 99.99, "p3", new Date(), 10,  new Date(), 3 ));
                add(new Offer( 30, "Blazers", 80, 55.367913,10.428155, 199.99, "p4", new Date(), 100,  new Date(),3 ));
            }}));

            add(new Store(4, "Levis", new Date(), new LatLng(55.3956519, 10.3724466), new ArrayList<Offer>(){{
                add(new Offer( 31, "Tshirts", 20, 55.3956519,10.3724466, 599.99, "p5", new Date(), 90,  new Date(), 4));
                add(new Offer( 32, "Hats", 30, 55.3956519,10.3724466, 499.99, "p6", new Date(), 10,  new Date(), 4));
                add(new Offer( 33, "Bag", 30, 55.3956519,10.3724466, 399.99, "p7", new Date(), 100,  new Date(),4));
                add(new Offer( 34, "Shirts", 30, 55.3956519,10.3724466, 299.99, "p8", new Date(), 100,  new Date(),4));

            }}));
        }};
    }

    @Override
    public ArrayList<Store> getStores() {
        return this.stores;
    }

    @Override
    public ArrayList<Offer> getOffers() {
        ArrayList<Offer>allOffers = new ArrayList<Offer>();
        for (Store store: this.stores){
            for (Offer offer: store.getOffers())
            {
                allOffers.add(offer);
            }
        }
        return allOffers;
    }
}
