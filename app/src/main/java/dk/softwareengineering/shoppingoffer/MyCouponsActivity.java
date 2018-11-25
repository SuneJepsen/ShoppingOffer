package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import Repository.ISessionRepository;
import Repository.SharedPreferenceRepository;
import businessLayer.Facade;


public class MyCouponsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    public static Context contextOfApplication;
    private Facade facade;
    public MyCouponsActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contextOfApplication = getApplicationContext();
        ISessionRepository session = new SharedPreferenceRepository(contextOfApplication);
        this.facade = new Facade(session);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        addCouponsFragment();
    }

    private void addCouponsFragment(){
        CouponsFragmentActivity couponsFragmentActivityFragment = new CouponsFragmentActivity(facade);
        fragmentTransaction.add(R.id.couponsContainer, couponsFragmentActivityFragment);
        fragmentTransaction.commit();
    }


}
