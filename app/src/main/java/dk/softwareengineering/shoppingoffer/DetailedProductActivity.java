package dk.softwareengineering.shoppingoffer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView productTitle = (TextView) findViewById(R.id.productTitle);

        Intent intent = getIntent();
        savedInstanceState = intent.getExtras();

        if (savedInstanceState != null){
            String productName = (String) savedInstanceState.get("ProductTitle");
            productTitle.setText(productName);
        }


        int imageResource = getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, this.getPackageName());
        firstImage.setImageResource(imageResource);
    }


}
