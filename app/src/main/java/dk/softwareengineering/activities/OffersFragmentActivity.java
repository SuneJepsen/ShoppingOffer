package dk.softwareengineering.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import businessLayer.IFacade;
import domain.Offer;

/**
 * Used for creating coupon fragments that display coupons in {@link MyCouponsActivity}.
 */
@SuppressLint("ValidFragment")
public class OffersFragmentActivity extends Fragment implements OfferAdapter.ItemClickListener {
    private final String TAG = "GoogleFragment";
    private View rootView;
    private OfferAdapter adapter;
    private final IFacade facade;
    private ArrayList<Offer> offers;

    public OffersFragmentActivity(IFacade facade) {
        this.facade = facade;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_offers_fragment, container, false);

        // Setup RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_offers);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapter = new OfferAdapter(rootView.getContext(),facade);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), DetailedOfferActivity.class);
        Offer offer = adapter.getItem(position);
        int offerId = offer.getId();
        intent.putExtra("offerId", offerId);
        startActivity(intent);
    }

    /**
     * Offers to load into the recyclerview.
     *
     * @param storeId Loads offers depending on the store.
     */
    public void addOffers(int storeId) {
        List<Offer> offers = facade.getStoreOffers(storeId);
        Log.i(TAG, "add " + offers.size() + " offers for store " + storeId);
        adapter.addOffers(offers);
        adapter.notifyDataSetChanged();
    }

    /**
     * Offers to remove from the recyclerview.
     *
     * @param storeId Removes offers depending on the store.
     */
    public void removeOffers(int storeId) {
        List<Offer> offers = facade.getStoreOffers(storeId);
        Log.i(TAG, "remove " + offers.size() + " offers for store " + storeId);
        adapter.removeOffers(offers);
        adapter.notifyDataSetChanged();
    }

}
