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

    public FakeOfferRepository(IStoreFactory storeFactory) {
        this.offers = storeFactory.getOffers();
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
