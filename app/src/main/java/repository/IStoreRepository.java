package repository;
import java.util.ArrayList;
import domain.Store;

/**
 * Interface for stores.
 */
public interface IStoreRepository {

    /**
     * Get individual stores using the store ID.
     *
     * @param storeIds The ID of the store to return
     *
     * @return Returns a store associated with the ID.
     */
    ArrayList<Store> getStoresByIds(ArrayList<Integer>storeIds);
}
