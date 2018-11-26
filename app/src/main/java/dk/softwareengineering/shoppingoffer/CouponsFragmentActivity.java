package dk.softwareengineering.shoppingoffer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import Repository.ISessionRepository;
import Repository.SharedPreferenceRepository;
import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Coupon;
import domain.Offer;


public class CouponsFragmentActivity extends Fragment implements  MyCouponsAdapter.ItemClickListener {

    private View rootView;
    private MyCouponsAdapter adapter;
    private final IFacade facade;
    private ArrayList<Offer> offers;
    public static Context contextOfApplication;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    @SuppressLint("ValidFragment")
    public CouponsFragmentActivity(IFacade facade) {

        this.facade = facade;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_coupons_fragment, container, false);

        // Retrieve user saved coupons
        List<Coupon> coupons = facade.GetUserCoupons("sune@student.sdu.dk");

        // Data to populate the RecyclerView with
        //ArrayList<Offer> offers = facade.getOffersByLatLong(55.55, 55.55);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int offerId = preferences.getInt("offerId", 0);

        ArrayList<Offer> offers = new ArrayList<>();
        Offer offer = facade.getOfferById(offerId);
        offers.add(offer);
        // Setup RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_coupons);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);
        adapter = new MyCouponsAdapter(rootView.getContext(), offers);
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
}
