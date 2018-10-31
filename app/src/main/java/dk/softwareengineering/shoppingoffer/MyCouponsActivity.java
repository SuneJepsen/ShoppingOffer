package dk.softwareengineering.shoppingoffer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MyCouponsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        addCouponsFragment();
    }

    private void addCouponsFragment(){
        CouponsFragmentActivity couponsFragmentActivityFragment = new CouponsFragmentActivity();
        fragmentTransaction.add(R.id.couponsContainer, couponsFragmentActivityFragment);
        fragmentTransaction.commit();
    }


}
