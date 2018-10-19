package distanceCalculator;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

/**
 * Created by Sune Jepsen on 19-10-2018.
 */

public class GoogleDistanceMatrixCalculator implements  IDistanceCalculator {
    private DistanceMatrixApiRequest distanceMatrixApiRequest;
    private GeoApiContext context;
    public GoogleDistanceMatrixCalculator(){
        context = new GeoApiContext.Builder().apiKey("AIzaSyCjm8Hx_PfLdgYgEHIArpubMxmecFF3GJ4").build();

    }
    public double calculateDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        DistanceMatrix distanceMatrix= null;
        distanceMatrixApiRequest = DistanceMatrixApi.newRequest(context);
        try {
            distanceMatrix = distanceMatrixApiRequest.origins(new LatLng(lat1, lon1))
                    .destinations(new LatLng(lat2, lon2))
                    .mode(TravelMode.WALKING).await();
            // Handle successful request.
        } catch (Exception e) {
            // Handle error
            System.out.println(e.getStackTrace());
        }
        double distance = 0.0;
        if(distanceMatrix != null){
            for (DistanceMatrixRow distanceRow: distanceMatrix.rows) {
                for (DistanceMatrixElement distanceMatrixElement: distanceRow.elements){
                    distance = distanceMatrixElement.distance.inMeters;
                }
            }
        }
        return distance;
    }
}
