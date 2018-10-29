package Repository;

import java.util.ArrayList;
import java.util.Date;
import domain.Offer;
import domain.Store;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class FakeOfferRepository implements IOfferRepository {
    private ArrayList<Offer> offers;

    public FakeOfferRepository() {
        this.offers = new ArrayList<Offer>(){{
            add(new Offer( 23, "New Autumn Set", 80, 55.367913,10.428155, 500, "newautumnset", new Date(), 100,  new Date(), new Store(2) ));
            add(new Offer( 24, "New Autumn Set", 70, 55.367913,10.428155, 400, "newautumnset", new Date(), 100,  new Date(), new Store(2) ));
            add(new Offer( 25, "New Autumn Set", 60, 55.367913,10.428155, 300, "newautumnset", new Date(), 100,  new Date(), new Store(2) ));
            add(new Offer( 26, "New Autumn Set", 50, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(2) ));
            add(new Offer( 27, "New Autumn Set", 40, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(2) ));
            add(new Offer( 28, "New Autumn Set", 30, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(3) ));
            add(new Offer( 29, "New Autumn Set", 80, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(3) ));
            add(new Offer( 30, "New Autumn Set", 70, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(3) ));
            add(new Offer( 31, "New Autumn Set", 60, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(3) ));
            add(new Offer( 32, "New Autumn Set", 50, 55.367913,10.428155, 200, "newautumnset", new Date(), 100,  new Date(), new Store(3) ));
        }};
    }

    @Override
    public Offer getOfferById(int id) {
        for (Offer offer: this.offers)
        {
            if (id== offer.getId())
            {
                return offer;
            }
        }
        //Fallback
        return this.offers.get(0);
    }
    @Override
    public ArrayList<Offer> getOfferByIds(ArrayList<Integer> offerIds) {
        ArrayList<Offer>offerToReturn = new ArrayList<Offer>();
        for (Integer offerId : offerIds){
            for (Offer offer: this.offers)
            {
                if (offerId == offer.getId())
                {
                    offerToReturn.add(offer);
                }
            }
        }
        if(offerToReturn.size() == 0)
            return this.offers; //Fallback
        else
            return offerToReturn;
    }
    @Override
    public ArrayList<Offer> getStoreOffers(Integer storeId) {
        ArrayList<Offer>storeOfferToReturn = new ArrayList<Offer>();
        for (Offer offer: this.offers)
        {
            if (storeId== offer.getStore().getId())
            {
                storeOfferToReturn.add(offer);
            }
        }
        if(storeOfferToReturn.size() == 0)
            return this.offers; //Fallback
        else
            return storeOfferToReturn;
    }
}
