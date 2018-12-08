package dk.softwareengineering.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import businessLayer.Facade;
import businessLayer.IFacade;
import domain.Store;
import geofence.GoogleGeofence;
import geofence.IGeofence;

import session.ISessionRepository;
import session.SharedPreferenceRepository;


/**
 * Used for displaying the map and offers from store.
 */
public class HomeScreenActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String ACTION = "GeofenceIntentService";
    private static final String TAG = "GoogleMaps";
    private static final int UPDATE_INTERVAL = 1000, FASTEST_INTERVAL = 1000, RADIUS = 1000 ;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    public final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private SupportMapFragment mapFragment;
    private Marker marker;
    private FragmentTransaction fragmentTransaction;
    private IFacade facade;
    private IGeofence googleGeofence;
    private List<Store> stores;
    public static Context contextOfApplication;
    private Map<Integer, Marker> storeMarkers;
    private Map<Integer, Circle> storeCircles;
    private OffersFragmentActivity offersFragmentActivity;

    public HomeScreenActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        setTitle(getResources().getString(R.string.title_activity_HomeScreen));
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navigation_home);
        navView.getMenu().getItem(0).setChecked(true);
        navView.setOnNavigationItemSelectedListener(this);

        contextOfApplication = getApplicationContext();
        ISessionRepository session = new SharedPreferenceRepository(contextOfApplication);
        this.facade = new Facade(session);
        stores = facade.getStores(0,0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        setupFragment();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();

        storeMarkers = new HashMap<>();
        storeCircles = new HashMap<>();

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter(ACTION));
    }

    /**
     * Used for receiving messages from the broadcast created by
     * {@link geofence.GeofenceIntentService}.
     */
    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Got intent");
            Log.i(TAG, "result " +  intent.getIntExtra("resultCode", 10000));
            Log.i(TAG, "store " + intent.getStringExtra("storeId"));
            String store = intent.getStringExtra("storeId");
            int storeId = Integer.valueOf(store);
            int transition = intent.getIntExtra("resultValue", 10000);
            Log.i(TAG, "result " + intent.getIntExtra("resultValue", 10000));

            for (Store s : stores) {
                if (s.getId() == Integer.valueOf(store)) {
                    if (transition == Geofence.GEOFENCE_TRANSITION_ENTER || transition == Geofence.GEOFENCE_TRANSITION_DWELL) {
                        LatLng store_position = new LatLng(s.getLocation().latitude, s.getLocation().longitude);
                        storeMarkers.put(storeId, mMap.addMarker(new MarkerOptions().position(store_position).title(s.getName())));
                        int strokeColor = 0xffff0000;
                        int shadeColor = 0x44ff0000;
                        CircleOptions circleOptions = new CircleOptions().center(store_position).radius(50).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(2);
                        storeCircles.put(storeId, mMap.addCircle(circleOptions));
                        offersFragmentActivity.addOffers(storeId);
                    } else if (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                        storeMarkers.get(storeId).remove();
                        storeMarkers.remove(storeId);
                        storeCircles.get(storeId).remove();
                        storeCircles.remove(storeId);
                        offersFragmentActivity.removeOffers(storeId);
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
        super.onDestroy();
    }

    /**
     * Setups the geofence around all stores with a predefined radius.
     */
    private void setupGeofence() {
        for (Store store : facade.getStores(0,0)) {
            googleGeofence.attachGeofences(store);
        }
    }

    private void setupFragment() {
        offersFragmentActivity = new OffersFragmentActivity(facade);
        fragmentTransaction.add(R.id.offersContainer, offersFragmentActivity);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // TODO: Update deprecated API
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                Log.i("GoogleMaps", "Initial location " + location.getLatitude() + ", " + location.getLongitude());
                LatLng current_position = new LatLng(location.getLatitude(), location.getLongitude());
                marker = mMap.addMarker(new MarkerOptions().position(current_position).title("Current Position")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bluedot_smaller)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(current_position));
            }
            setupLocationListener();
        } catch (SecurityException e) {
            Log.e("GoogleMaps", "No Location Permissions found");
        }
    }

    private void setupLocationListener() throws SecurityException {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        //TODO: Update deprecated API
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    /**
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "permission already granted");
            mapFragment.getMapAsync(this);
            setupGeofence();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "permissions granted");
                    mapFragment.getMapAsync(this);
                    setupGeofence();
                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "conntected");
        googleGeofence = new GoogleGeofence(this, googleApiClient, RADIUS
                , 5);
        getLocationPermission();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("GoogleMaps", "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("GoogleMaps", "Connection Failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng current_position = new LatLng(location.getLatitude(), location.getLongitude());
        if (marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions().position(current_position).title("Current Position")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bluedot_smaller)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current_position));
        Log.i("GoogleMaps", "current location changed: " + location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_coupons:
                intent = new Intent(getBaseContext(), MyCouponsActivity.class);
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
