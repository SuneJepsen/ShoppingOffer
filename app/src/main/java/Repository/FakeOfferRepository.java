package Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public class FakeOfferRepository implements IOfferRepository {

    private ArrayList<Offer> offers;


    public FakeOfferRepository() {
        this.offers = new ArrayList<Offer>(){{
            add(new Offer( 23, "New Autumn Set", 80, 55.367913,10.428155, 500, "newautumnset", new Date(), 100,  new Date() ));
            add(new Offer( 24, "New Autumn Set", 80, 55.367913,10.428155, 400, "newautumnset", new Date(), 100,  new Date() ));
            add(new Offer( 25, "New Autumn Set", 80, 55.367913,10.428155, 300, "newautumnset", new Date(), 100,  new Date() ));
            add(new Offer( 26, "New Autumn Set", 80, 55.367913,10.428155, 200, "pants", new Date(), 100,  new Date() ));
        }};
    }

    public List<Offer> createMockList() {
        List<Offer> o = new ArrayList<>();

        o.add(new Offer( 23, "New Autumn Set", 80, 55.367913,10.428155, 500, "newautumnset", new Date(), 100,  new Date() ));
        o.add(new Offer( 24, "New Autumn Set", 80, 55.367913,10.428155, 400, "newautumnset", new Date(), 100,  new Date() ));
        o.add(new Offer( 25, "New Autumn Set", 80, 55.367913,10.428155, 300, "newautumnset", new Date(), 100,  new Date() ));
        o.add(new Offer( 26, "Pants", 80, 55.367913,10.428155, 200, "pants", new Date(), 100,  new Date() ));
        return o;
    }

    @Override
    public Offer getOfferById(int id) {
        return this.offers.get(0);
    }
}
