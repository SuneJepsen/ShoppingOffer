package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import Repository.ISessionRepository;
import Repository.SharedPreferenceRepository;
import businessLayer.Facade;


public class MyCouponsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
        setTitle(getResources().getString(R.string.title_activity_MyCoupons));
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_coupon);
        navView.getMenu().getItem(1).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        addCouponsFragment();
    }

    private void addCouponsFragment(){
        CouponsFragmentActivity couponsFragmentActivityFragment = new CouponsFragmentActivity(facade);
        fragmentTransaction.add(R.id.couponsContainer, couponsFragmentActivityFragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_map:
                intent = new Intent(getBaseContext(), HomeScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.action_profile:
                intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
