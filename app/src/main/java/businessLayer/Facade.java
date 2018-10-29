package businessLayer;

import java.util.ArrayList;
import java.util.List;

import Repository.FakeOfferRepository;
import Repository.IOfferRepository;
import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class Facade implements IFacade {
    private final IOfferRepository offerRepository;

    public Facade(){
        this.offerRepository = new FakeOfferRepository();
    }
    @Override
    public Offer getOfferById(int id) {
        return this.offerRepository.getOfferById(id);
    }

    @Override
    public ArrayList<Offer> getOffersByLatLong(double latitude, double longitude) {
        return this.offerRepository.getOfferByIds(new ArrayList<Integer>(){{add(1);add(2);}});
    }
    @Override
    public ArrayList<Offer> getOffers() {
        return this.offerRepository.getOffers();
    }
}
