package businessLayer;

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
}
