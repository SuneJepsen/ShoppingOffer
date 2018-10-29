package dk.softwareengineering.shoppingoffer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeScreenActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int UPDATE_INTERVAL = 1000, FASTEST_INTERVAL = 1000 ;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    public final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private SupportMapFragment mapFragment;
    private Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                Log.i("GoogleMaps", "Initial location " + location.getLatitude() + ", " + location.getLongitude());
                LatLng current_position = new LatLng(location.getLatitude(), location.getLongitude());
                marker = mMap.addMarker(new MarkerOptions().position(current_position).title("Current Position"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(current_position));
            }

            setupLocationListener();
        } catch (SecurityException e) {
            Log.e("GoogleMaps", "No Location Permissions found");
        }
    }

    private void setupLocationListener() throws SecurityException {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mapFragment.getMapAsync(this);
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
                    mapFragment.getMapAsync(this);
                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
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
        marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(current_position).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current_position));
        Log.i("GoogleMaps", "current location changed: " + location.getLatitude() + ", " + location.getLongitude());
    }
}
