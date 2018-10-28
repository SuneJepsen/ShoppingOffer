package dk.softwareengineering.shoppingoffer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

/**
 * @TODO comment code
 */
public class DetailedProductActivity extends AppCompatActivity {

    private static  final String TAG ="TestSuneActivity";
    private final IFacade facade;

    public DetailedProductActivity() {
        this.facade = new Facade();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);
        Log.d(TAG, "onCreate: started.");
        Offer offer = facade.getOfferById(23);
        Log.d(TAG, offer.getImagePath());
        ImageView firstImage = (ImageView) findViewById(R.id.testProductImage);
        int imageResource = getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, this.getPackageName());
        firstImage.setImageResource(imageResource);
    }
}
