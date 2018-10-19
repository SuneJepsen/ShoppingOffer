package distanceCalculator;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public interface IDistanceCalculator {
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference.
     *
     * lat1, lon1 Start point, lat2, lon2 End point, el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public double calculateDistance(double lat1, double lat2, double lon1,
                                    double lon2, double el1, double el2);
}
