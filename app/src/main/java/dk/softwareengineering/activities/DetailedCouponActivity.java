package dk.softwareengineering.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Coupon;
import session.ISessionRepository;
import session.SharedPreferenceRepository;

/**
 * Used for displaying detailed information about a coupon.
 */
public class DetailedCouponActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static Context contextOfApplication;

    private long timeLeftInMilliseconds;
    private Button txt_countDown;

    public DetailedCouponActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();
        ISessionRepository session = new SharedPreferenceRepository(contextOfApplication);
        IFacade facade = new Facade(session);

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
        txt_countDown = (Button) findViewById(R.id.countDown);

        Intent intent = getIntent();
        savedInstanceState = intent.getExtras();

        if (savedInstanceState != null){
            int couponId = (Integer) savedInstanceState.getInt("couponId");
            List<Coupon> coupons = facade.getUserCoupons("sune@student.sdu.dk");
            for (Coupon c : coupons) {
                if (c.getId() == couponId) {
                    int imageResource = getResources().getIdentifier("@drawable/"+c.getOffer().getImagePath(), null, this.getPackageName());
                    img_couponImage.setImageResource(imageResource);
                    txt_couponTitle.setText(c.getOffer().getTitle());
                    txt_store.setText(facade.getStoreById(c.getOffer().getStoreId()).getName());
                    txt_price.setText(Double.toString(c.getOffer().getPrice()) + " kr");
                    startTimer(c.getCreatedDat());
                }
            }
        }
    }

    /**
     * A countdown timer is created every time a coupon is shown with the remaining time until
     * deadline (10 minutes after created).
     *
     * @param createdDate The date the coupon is created.
     */
    public void startTimer(final Date createdDate){
        // = 10 min
        long RESERVATION_TIME = 600000;
        long duration = createdDate.getTime() + RESERVATION_TIME - new Date().getTime();

        //Timer
        CountDownTimer countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    /**
     * Updates the timer.
     */
    public void updateTimer() {
        // Casting minutes and seconds to int.
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000/1000;
        String timeLeftText;

        timeLeftText = ""  + minutes;
        timeLeftText += ":";
        // If seconds are below 10 then we want the digit to still be 2 digits with 0 in front of
        // 1 to 9.
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        txt_countDown.setText(timeLeftText);
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
