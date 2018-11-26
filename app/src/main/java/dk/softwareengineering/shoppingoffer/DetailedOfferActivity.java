package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
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
public class DetailedOfferActivity extends AppCompatActivity {

    private IFacade facade;
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

        List<Coupon> coupons  = session.GetUserCoupons("sune@student.sdu.dk");
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);

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
                facade.SaveOfferToUser("sune@student.sdu.dk",23);
                Intent intent = new Intent(DetailedOfferActivity.this, MyCouponsActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        savedInstanceState = intent.getExtras();

        if (savedInstanceState != null){
            int offerId = (Integer) savedInstanceState.getInt("offerId");
            Offer offer = facade.getOfferById(offerId);
            int imageResource = getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, this.getPackageName());
            img_offerImage.setImageResource(imageResource);
            txt_offerTitle.setText(offer.getTitle());
            txt_numberOfCoupons.setText(Integer.toString(offer.getAmountCounter()));
            txt_offerPrice.setText(Double.toString(offer.getPrice()));
            txt_timeLimit.setText(offer.getTimeCounter().toString());
        }

    }


}
