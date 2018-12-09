package businessLayer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.FakeCouponRepository;
import repository.FakeOfferRepository;
import factory.FakeStoreFactory;
import repository.FakeStoreRepository;
import factory.FakeCustomerFactory;
import repository.IOfferRepository;
import session.ISessionRepository;
import factory.IStoreFactory;
import factory.ICustomerFactory;
import domain.Coupon;
import domain.Offer;
import domain.Store;

/**
 * Used as a facade to mask complexity.
 */
public class Facade implements IFacade {
    private final IOfferRepository offerRepository;
    private final FakeStoreRepository storeRepository;
    private final ISessionRepository prefRepo;
    private final FakeCouponRepository couponRepository;
    private final long RESERVATION_TIME = 600000; // 10 min

    public Facade(ISessionRepository prefRepo){
        this.prefRepo = prefRepo;
        IStoreFactory storefactory = new FakeStoreFactory();
        ICustomerFactory customerFactory = new FakeCustomerFactory();
        this.couponRepository = new FakeCouponRepository(prefRepo);
        this.offerRepository = new FakeOfferRepository(storefactory,customerFactory,prefRepo);
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
    public Store getStoreById(final int storeId) {
        ArrayList<Integer> storeIds = new ArrayList<Integer>(){{add(storeId);}};
        ArrayList<Store> stores = this.storeRepository.getStoresByIds(storeIds);
        return stores.size() > 0 ? stores.get(0) : null ;
    }

    @Override
    public void saveOfferToCustomer(String customerId, int offerId){
        offerRepository.saveOfferToCustomer(customerId,offerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(String customerId){

        List<Coupon> coupons = couponRepository.getCustomerCoupons(customerId);

        //Remove expired coupons
        for(int i = coupons.size()-1; i >= 0; i--) {
            long expirationDate = coupons.get(i).getCreatedDate().getTime() + RESERVATION_TIME;

            if(new Date().getTime() > expirationDate){
                coupons.remove(i);
            }
        }
        return coupons;
    }
}
