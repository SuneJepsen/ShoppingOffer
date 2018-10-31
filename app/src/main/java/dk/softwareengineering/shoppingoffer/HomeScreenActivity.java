package dk.softwareengineering.shoppingoffer;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @TODO Comment and comment purpose of class
 */
public class HomeScreenActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        addOffersFragment();
        setupMenuBar();
    }

    private void setupMenuBar() {
        // Menu go to MyCoupons
        Button btn_myCoupons = (Button) findViewById(R.id.btn_myCoupons);
        btn_myCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeScreenActivity.this, MyCouponsActivity.class);
                startActivity(i);
            }
        });
    }

    private void addOffersFragment(){
        OffersFragmentActivity offersFragmentActivity = new OffersFragmentActivity();
        fragmentTransaction.add(R.id.offersContainer, offersFragmentActivity);
        fragmentTransaction.commit();
    }
}
