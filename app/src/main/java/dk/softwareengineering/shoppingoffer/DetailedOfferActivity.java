package dk.softwareengineering.shoppingoffer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

/**
 * @TODO comment code
 */
public class DetailedOfferActivity extends AppCompatActivity {

    private final IFacade facade;

    public DetailedOfferActivity() {
        this.facade = new Facade();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);

        ImageView img_offerImage = (ImageView) findViewById(R.id.img_offerImage);
        TextView txt_offerTitle = (TextView) findViewById(R.id.txt_offerTitle);

        TextView txt_numberOfCoupons = (TextView) findViewById(R.id.txtNumberOfCoupons);
        TextView txt_offerPrice = (TextView) findViewById(R.id.txtPrice);
        TextView txt_timeLimit = (TextView) findViewById(R.id.txtTimeLimit);


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
