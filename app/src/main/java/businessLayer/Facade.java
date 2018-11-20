package businessLayer;
import java.util.ArrayList;
import Repository.FakeOfferRepository;
import Repository.FakeStoreFactory;
import Repository.FakeStoreRepository;
import Repository.IOfferRepository;
import Repository.IStoreFactory;
import domain.Offer;
import domain.Store;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class Facade implements IFacade {
    private final IOfferRepository offerRepository;
    private final FakeStoreRepository storeRepository;

    public Facade(){
        IStoreFactory factory = new FakeStoreFactory();
        this.offerRepository = new FakeOfferRepository(factory);
        this.storeRepository = new FakeStoreRepository(factory);
    }
    @Override
    public Offer getOfferById(int id) {
        return this.offerRepository.getOfferById(id);
    }
    @Override
    public ArrayList<Offer> getOffersByLatLong(double latitude, double longitude) {
        //ToDo: Find stores by distance and their offers
        return this.offerRepository.getOfferByIds(new ArrayList<Integer>(){{add(1);add(2);}});
    }
    @Override
    public ArrayList<Offer> getStoreOffers(Integer storeId) {
        return this.offerRepository.getStoreOffers(storeId);
    }
    @Override
    public ArrayList<Store> getStores(double latitude, double longitude) {
        //ToDo: Find stores by distance
        return this.storeRepository.getStoresByIds(new ArrayList<Integer>(){{add(1);add(2);add(3);add(4);}});
    }
}
