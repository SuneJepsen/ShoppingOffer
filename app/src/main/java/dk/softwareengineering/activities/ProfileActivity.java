package dk.softwareengineering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.title_activity_Profile));
        setContentView(R.layout.activity_profile);
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_profile);
        navView.getMenu().getItem(2).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_map:
                intent = new Intent(getBaseContext(), HomeScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.action_coupons:
                intent = new Intent(getBaseContext(), MyCouponsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
