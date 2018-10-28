package Repository;

import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public interface IOfferRepository {
    Offer getOfferById(int id);
}
