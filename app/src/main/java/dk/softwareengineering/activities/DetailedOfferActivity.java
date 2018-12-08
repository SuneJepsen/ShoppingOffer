package dk.softwareengineering.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import session.ISessionRepository;
import session.SharedPreferenceRepository;
import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

/**
 * Used for displaying detailed information about a Offer.
 */
public class DetailedOfferActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private IFacade facade;
    private int offerId;
    public static Context contextOfApplication;

    public DetailedOfferActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();
        ISessionRepository session = new SharedPreferenceRepository(contextOfApplication);
        this.facade = new Facade(session);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);
        setTitle(getResources().getString(R.string.title_activity_Offer));
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_offer);
        navView.getMenu().getItem(1).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);

        ImageView img_offerImage = (ImageView) findViewById(R.id.img_offerImage);
        TextView txt_offerTitle = (TextView) findViewById(R.id.txt_offerTitle);
        TextView txt_store = (TextView) findViewById(R.id.txt_store);
        TextView txt_stock = (TextView) findViewById(R.id.txtStock);
        TextView txt_offerPrice = (TextView) findViewById(R.id.txtPrice);
        TextView txt_deadline = (TextView) findViewById(R.id.txt_deadline);
        Button btn_reserve = (Button) findViewById(R.id.btnReserve);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            facade.saveOfferToCustomer("sune@student.sdu.dk",offerId);
            Intent intent = new Intent(DetailedOfferActivity.this, MyCouponsActivity.class);

            startActivity(intent);
            }
        });

        Intent intent = getIntent();
        savedInstanceState = intent.getExtras();

        if (savedInstanceState != null){
            offerId = (Integer) savedInstanceState.getInt("offerId");
            Offer offer = facade.getOfferById(offerId);
            int imageResource = getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, this.getPackageName());
            img_offerImage.setImageResource(imageResource);
            txt_offerTitle.setText(offer.getTitle());
            txt_store.setText(facade.getStoreById(offer.getStoreId()).getName());
            txt_stock.setText(Integer.toString(offer.getAmountCounter()));
            txt_offerPrice.setText(Double.toString(offer.getPrice()) + " kr");
            txt_deadline.setText(new SimpleDateFormat("hh:mm").format(offer.getTimeCounter()));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_map:
                intent = new Intent(getBaseContext(), HomeScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.action_coupons:
                intent = new Intent(getBaseContext(), MyCouponsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_profile:
                intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
