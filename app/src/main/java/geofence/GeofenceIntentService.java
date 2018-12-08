package geofence;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

/**
 * Used for sending out a geofence service that will listen for when a customer walks into/out of
 * the geofence.
 */
public class GeofenceIntentService extends IntentService {
    public static final String ACTION = "GeofenceIntentService";
    private String TAG = "GoogleIntentService";

    public GeofenceIntentService() {
        super("Geofence Service");
        Log.i(TAG, "constructed");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "created");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent");
        GeofencingEvent geofenceEvent = GeofencingEvent.fromIntent(intent);
        if (geofenceEvent.hasError()) {
            int errorMessage = geofenceEvent.getErrorCode();
            Log.e(TAG, "errorcode: " + errorMessage);
            return;
        }

        // Test that the reported transition was of interest.
        if ((geofenceEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_ENTER)
                || (geofenceEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_EXIT)
                || (geofenceEvent.getGeofenceTransition() == Geofence.GEOFENCE_TRANSITION_DWELL)) {
            try {
                // Send notification and log the transition details.
                for (Geofence g : geofenceEvent.getTriggeringGeofences()) {
                    Log.i(TAG, "id: " + g.getRequestId());

                    Intent in = new Intent(ACTION);
                    in.putExtra("resultCode", Activity.RESULT_OK);
                    in.putExtra("storeId", g.getRequestId());
                    in.putExtra("resultValue", geofenceEvent.getGeofenceTransition());
                    // Fire the broadcast with intent packaged.
                    LocalBroadcastManager.getInstance(this).sendBroadcast(in);
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            // Log the error.
            Log.e(TAG, "Geofence is not enter or exit");
        }
    }
}