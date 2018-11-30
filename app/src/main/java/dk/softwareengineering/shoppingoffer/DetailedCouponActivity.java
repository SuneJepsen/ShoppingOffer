package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Repository.ISessionRepository;
import Repository.SharedPreferenceRepository;
import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Coupon;
import domain.Offer;

/**
 * @TODO comment code
 */
public class DetailedCouponActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private IFacade facade;
    private int couponId;
    public static Context contextOfApplication;

    public DetailedCouponActivity() {

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
        setContentView(R.layout.activity_detailed_coupon);
        setTitle(getResources().getString(R.string.title_activity_Coupon));
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_coupon_detail);
        navView.getMenu().getItem(1).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);

        ImageView img_couponImage = (ImageView) findViewById(R.id.img_couponImage);
        TextView txt_couponTitle = (TextView) findViewById(R.id.txt_couponTitle);
        TextView txt_store = (TextView) findViewById(R.id.txt_couponstore);
        TextView txt_price = (TextView) findViewById(R.id.txt_couponPrice);
        TextView txt_description = (TextView) findViewById(R.id.txt_couponDescription);
        Button txt_countDown = (Button) findViewById(R.id.countDown);

        Intent intent = getIntent();
        savedInstanceState = intent.getExtras();

        if (savedInstanceState != null){
            couponId = (Integer) savedInstanceState.getInt("couponId");
            List<Coupon> coupons = facade.getUserCoupons("sune@student.sdu.dk");
            for (Coupon c : coupons) {
                if (c.getId() == couponId) {
                    int imageResource = getResources().getIdentifier("@drawable/"+c.getOffer().getImagePath(), null, this.getPackageName());
                    img_couponImage.setImageResource(imageResource);
                    txt_couponTitle.setText(c.getOffer().getTitle());
                    txt_store.setText(facade.getStoreById(c.getOffer().getStoreId()).getName());
                    txt_price.setText(Double.toString(c.getOffer().getPrice()));
                    //txt_description.setText();
                    //txt_countDown = new TimerCount
                }
            }
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
