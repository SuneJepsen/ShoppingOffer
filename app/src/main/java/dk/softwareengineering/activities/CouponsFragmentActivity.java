package dk.softwareengineering.activities;

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

import businessLayer.IFacade;
import domain.Coupon;
import domain.Offer;

/**
 * Used for creating coupon fragments that display coupons in {@link MyCouponsActivity}.
 */
@SuppressLint("ValidFragment")
public class CouponsFragmentActivity extends Fragment implements  MyCouponsAdapter.ItemClickListener {

    private MyCouponsAdapter adapter;
    private final IFacade facade;

    @SuppressLint("ValidFragment")
    public CouponsFragmentActivity(IFacade facade) {

        this.facade = facade;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_coupons_fragment, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int offerId = preferences.getInt("offerId", 0);

        ArrayList<Offer> offers = new ArrayList<>();
        Offer offer = facade.getOfferById(offerId);
        offers.add(offer);
        // Setup RecyclerView.
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_coupons);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);
        adapter = new MyCouponsAdapter(rootView.getContext(), facade);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), DetailedCouponActivity.class);
        Coupon coupon = adapter.getItem(position);
        intent.putExtra("couponId", coupon.getId());
        startActivity(intent);
    }
}
