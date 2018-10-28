package businessLayer;

import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public interface IFacade {
    Offer getOfferById(int id);
}
