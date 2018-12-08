package geofence;

import android.app.PendingIntent;
import domain.Store;

/**
 * Interface for geofences.
 */
public interface IGeofence {

    /**
     * Attaches a geofence to a store.
     *
     * @param store Store to attach the geofence to.
     */
    void attachGeofences(Store store);

    /**
     * Removes the geofence attached to the store.
     *
     * @param intent Sends an intent with the purpose dettaching a geofence from a store.
     */
    void deleteGeofence(PendingIntent intent);
}
