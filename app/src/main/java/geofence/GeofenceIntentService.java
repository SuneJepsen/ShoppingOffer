package geofence;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;


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
                    //sendNotification(id, geofenceEvent.isEnter());

                    Intent in = new Intent(ACTION);
                    in.putExtra("resultCode", Activity.RESULT_OK);
                    in.putExtra("storeId", g.getRequestId());
                    in.putExtra("resultValue", geofenceEvent.getGeofenceTransition());
                    // Fire the broadcast with intent packaged
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

    /*private void sendNotification(String id, final boolean isEnter) {
        Log.i(TAG, "sendNotification");

        try {

            final Position type = getDestinationType(id);
            String routeId = id.split(":")[0];
            firebase.getRoute(routeId, new OnGetRoutesListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(List<Route> routes) {
                    String msg = null;

                    if (routes.isEmpty()) {
                        Log.i(TAG, "route does not exist");
                        return;
                    }

                    if (type == Position.ENDDESTINATION && isEnter) {
                        msg = "Bike route ended at " + routes.get(0).getEndDest().getStreetName();
                    } else if (type == Position.STARTDESTINATION) {
                        msg = "Bike route started at " + routes.get(0).getStartDest().getStreetName();
                    } else {
                        return;
                    }

                    Intent notificationIntent = new Intent(getApplicationContext(), LoginActivity.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        int num = (int) System.currentTimeMillis();
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                        stackBuilder.addParentStack(HomeActivity.class);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(num, PendingIntent.FLAG_UPDATE_CURRENT);

                        // Creating and sending Notification
                        NotificationManager notificationMng =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Log.i(TAG, "notify");
                        notificationMng.notify(num,
                                createNotification(msg, notificationPendingIntent));
                    }
                }

                @Override
                public void onFailed(String errorMessage) {
                    Log.i(TAG, errorMessage);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }*/


    // Create a notification
    /*private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.drawable.bike_icon)
                .setColor(Color.RED)
                .setContentTitle(msg)
                .setContentText("Geofence Notification!")
                .setContentIntent(notificationPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);
        Log.i(TAG, "createNotification");
        return notificationBuilder.build();
    }*/



}