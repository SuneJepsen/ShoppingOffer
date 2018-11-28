package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Repository.ISessionRepository;
import Repository.SharedPreferenceRepository;
import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

/**
 * @TODO comment code
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

        /*contextOfApplication = getApplicationContext();

        ISessionRepository session = new SharedPreferenceRepository(contextOfApplication);

        List<Coupon> coupons  = session.getUserCoupons("sune@student.sdu.dk");
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);
        setTitle(getResources().getString(R.string.title_activity_Coupon));
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_offer);
        navView.getMenu().getItem(1).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        ImageView img_offerImage = (ImageView) findViewById(R.id.img_offerImage);
        TextView txt_offerTitle = (TextView) findViewById(R.id.txt_offerTitle);

        TextView txt_numberOfCoupons = (TextView) findViewById(R.id.txtNumberOfCoupons);
        TextView txt_offerPrice = (TextView) findViewById(R.id.txtPrice);
        TextView txt_timeLimit = (TextView) findViewById(R.id.txtTimeLimit);
        Button btn_reserve = (Button) findViewById(R.id.btnReserve);
        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ToDo: insert userId and the offer Id
                facade.saveOfferToUser("sune@student.sdu.dk",offerId);
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
            txt_numberOfCoupons.setText(Integer.toString(offer.getAmountCounter()));
            txt_offerPrice.setText(Double.toString(offer.getPrice()));
            txt_timeLimit.setText(offer.getTimeCounter().toString());
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
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
