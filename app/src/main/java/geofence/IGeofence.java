package geofence;

import android.app.PendingIntent;
import android.content.Intent;

import domain.Store;
import okhttp3.Route;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public interface IGeofence {
    void attachGeofences(Store store);

    void deleteGeofence(PendingIntent intent);

}
