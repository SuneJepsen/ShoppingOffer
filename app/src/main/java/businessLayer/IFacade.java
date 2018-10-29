package businessLayer;

import java.util.ArrayList;
import java.util.List;

import domain.Offer;

/**
 * Created by Sune Jepsen on 27-10-2018.
 */

public interface IFacade {
    Offer getOfferById(int id);
    ArrayList<Offer> getOffersByLatLong(double latitude, double longitude);
}
