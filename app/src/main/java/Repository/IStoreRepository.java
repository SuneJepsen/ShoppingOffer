package Repository;
import java.util.ArrayList;
import domain.Store;

/**
 * Created by Sune Jepsen on 29-10-2018.
 */

public interface IStoreRepository {
    ArrayList<Store> getStoresByIds(ArrayList<Integer>storeIds);
}
