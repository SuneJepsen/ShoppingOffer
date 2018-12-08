package repository;

import java.util.ArrayList;

import domain.Store;
import factory.IStoreFactory;

/**
 * Used for fake stores. Will be replaced by actual data storage system.
 */
public class FakeStoreRepository implements IStoreRepository {
    private ArrayList<Store> stores;
    public FakeStoreRepository(IStoreFactory storeFactory) {
        this.stores = storeFactory.getStores();
    }

    @Override
    public ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) {
        ArrayList<Store> storeList = new ArrayList<Store>();
        for (int id : storeIds) {
            for (Store store : stores) {
                if (id == store.getId()) {
                    storeList.add(store);
                    break;
                }
            }
        }
        return storeList;
    }
}
