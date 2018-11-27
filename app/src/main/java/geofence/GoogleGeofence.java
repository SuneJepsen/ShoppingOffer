package geofence;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import domain.Store;


/**
 * Geofencing combines awareness of the user's current location with
 * awareness of the user's proximity to locations that may be of interest.
 */

public class GoogleGeofence implements IGeofence, ResultCallback<Status> {
    private int radius;
    private int bufferInMinutes;
    private int loiteringInSeconds = 10;
    private GoogleApiClient googleApiClient;
    private Context context;
    private String TAG = "GoogleGeofence";

    public GoogleGeofence(Context context, GoogleApiClient client, int radiusInMeters, int bufferTimeInMinutes) {
        this.bufferInMinutes = bufferTimeInMinutes;
        this.radius = radiusInMeters;
        this.context = context;
        this.googleApiClient = client;
    }

    public void attachGeofences(Store store) {
        // TODO: schedule geofences
        Log.i(TAG, "attach geofences");
        Geofence fence = createGeofence(String.valueOf(store.getId()), store.getLocation());
        addGeofence(createGeofencingRequest(fence));


    }

    private Geofence createGeofence(String id, LatLng location) {
        Geofence geofence = new Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(
                        location.latitude,
                        location.longitude,
                        radius
                )
                .setExpirationDuration(2 * 60 * 1000 * bufferInMinutes)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_DWELL |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .setLoiteringDelay(loiteringInSeconds * 1000)
                .build();
        Log.i(TAG, "create geofence");
        return geofence;
    }

    private GeofencingRequest createGeofencingRequest(Geofence geofence) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(geofence);
        Log.i(TAG, "createGeofenceRequest");
        return builder.build();
    }

    public void addGeofence(GeofencingRequest request) {
        try {
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    request,
                    getGeofencePendingIntent()
            ).setResultCallback(this);
            Log.i(TAG, "add geofence");
        } catch (SecurityException ex) {
            Log.i(TAG, ex.getMessage());
        }
    }

    public void deleteGeofence(PendingIntent intent) {
        LocationServices.GeofencingApi.removeGeofences(
                googleApiClient,
                intent
        ).setResultCallback(this);
    }

    private PendingIntent getGeofencePendingIntent() {
        // Start service ??
        Intent intent = new Intent(context, GeofenceIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        Log.i(TAG, "get pending intent");
        return PendingIntent.getService(context, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult " + status.getStatusCode());
    }


    /*@Override
    public IGeofenceEvent getGeofenceEvent(Intent intent) {
        return new GoogleGeofenceEvent(intent);
    }*/

}
