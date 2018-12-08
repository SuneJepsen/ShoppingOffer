package repository;

import java.util.ArrayList;

import domain.Store;
import factory.IStoreFactory;

/**
 * Created by Sune Jepsen on 29-10-2018.
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
