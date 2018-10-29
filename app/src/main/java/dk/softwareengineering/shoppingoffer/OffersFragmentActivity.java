package dk.softwareengineering.shoppingoffer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

/**
 * @TODO Comment and comment purpose of class
 */
public class OffersFragmentActivity extends Fragment implements OfferAdapter.ItemClickListener {

    private View rootView;
    private OfferAdapter adapter;
    private final IFacade facade;
    private ArrayList<Offer> offers;

    public OffersFragmentActivity() {
        this.facade = new Facade();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_offers, container, false);


        // Data to populate the RecyclerView with
        ArrayList<Offer> offers = facade.getOffersByLatLong(55.55, 55.55);
        //offers = facade.getOffers();

        // Setup RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_offers);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapter = new OfferAdapter(rootView.getContext(), offers);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), DetailedOfferActivity.class);
        Offer offer = adapter.getItem(position);

        int offerID = offer.getId();

        intent.putExtra("offerID", offerID);
        startActivity(intent);

    }

}
