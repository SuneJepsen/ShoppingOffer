package dk.softwareengineering.shoppingoffer;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @TODO Comment and comment purpose of class
 */
public class OffersFragmentActivity extends Fragment implements OfferAdapter.ItemClickListener {

    private View rootView;
    private OfferAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_offers, container, false);


        // data to populate the RecyclerView with
        ArrayList<Integer> viewImages = new ArrayList<>();
        viewImages.add(Color.BLUE);
        viewImages.add(Color.YELLOW);
        viewImages.add(Color.MAGENTA);
        viewImages.add(Color.RED);
        viewImages.add(Color.BLACK);

        ArrayList<String> productNames = new ArrayList<>();
        productNames.add("Jeans");
        productNames.add("Shirt");
        productNames.add("Pants");
        productNames.add("Shirt");
        productNames.add("Shoes");

        // Setup RecyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_offers);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new OfferAdapter(rootView.getContext(), viewImages, productNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), DetailedProductActivity.class);
        String productTitle = adapter.getItem(position);
        intent.putExtra("ProductTitle", productTitle);
        startActivity(intent);

    }

}
