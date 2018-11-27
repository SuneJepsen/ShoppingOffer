package Repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import domain.Offer;
import domain.Store;
import domain.User;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class FakeOfferRepository implements IOfferRepository {
    private final ArrayList<User> users;
    private final ISessionRepository prefRepo;
    private ArrayList<Offer> offers;

    public FakeOfferRepository(IStoreFactory storeFactory, IUserFactory userFactory, ISessionRepository prefRepo) {
        this.offers = storeFactory.getOffers();
        this.users = userFactory.getUsers();
        this.prefRepo = prefRepo;
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

    public void SaveOfferToUser(String userId, int offerId){
        Offer offer = getOfferById(offerId);
        prefRepo.SaveOfferToUser(userId,offer);
    }





}
