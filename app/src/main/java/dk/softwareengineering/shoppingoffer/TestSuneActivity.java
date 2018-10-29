package dk.softwareengineering.shoppingoffer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import java.util.ArrayList;

import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Offer;

public class TestSuneActivity extends AppCompatActivity {

    private static  final String TAG ="TestSuneActivity";
    private final IFacade facade;

    public TestSuneActivity() {
        this.facade = new Facade();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sune);
//        Log.d(TAG, "onCreate: started.");
//        Offer offer = facade.getOfferById(23);
//        ArrayList<Offer> offers =  facade.getOffersByLatLong(55.55,55.55);
//        Log.d(TAG, offer.getImagePath());
//        ImageView firstImage = (ImageView) findViewById(R.id.firstImage);
//        int imageResource = getResources().getIdentifier("@drawable/"+offer.getImagePath(), null, this.getPackageName());
//        firstImage.setImageResource(imageResource);
    }
}
