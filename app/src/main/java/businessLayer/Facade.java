package businessLayer;
import java.util.ArrayList;
import java.util.List;

import Repository.FakeCouponRepository;
import Repository.FakeOfferRepository;
import Repository.FakeStoreFactory;
import Repository.FakeStoreRepository;
import Repository.FakeUserFactory;
import Repository.IOfferRepository;
import Repository.ISessionRepository;
import Repository.IStoreFactory;
import Repository.IUserFactory;
import domain.Coupon;
import domain.Offer;
import domain.Store;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class Facade implements IFacade {
    private final IOfferRepository offerRepository;
    private final FakeStoreRepository storeRepository;
    private final ISessionRepository prefRepo;
    private final FakeCouponRepository couponRepository;

    public Facade(ISessionRepository prefRepo){
        this.prefRepo = prefRepo;
        IStoreFactory storefactory = new FakeStoreFactory();
        IUserFactory  userFactory = new FakeUserFactory();
        this.couponRepository = new FakeCouponRepository(prefRepo);
        this.offerRepository = new FakeOfferRepository(storefactory,userFactory,prefRepo);
        this.storeRepository = new FakeStoreRepository(storefactory);
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

    @Override
    public Store getStoreById(int storeId) {
        ArrayList<Integer> storeIds = new ArrayList<Integer>();
        storeIds.add(storeId);
        ArrayList<Store> stores = this.storeRepository.getStoresByIds(storeIds);
        return stores.size() > 0 ? stores.get(0) : null ;
    }

    @Override
    public void saveOfferToUser(String userId, int offerId){
        offerRepository.SaveOfferToUser(userId,offerId);
    }

    @Override
    public List<Coupon> getUserCoupons(String userId){
        return couponRepository.GetUserCoupons(userId);
    }

}
