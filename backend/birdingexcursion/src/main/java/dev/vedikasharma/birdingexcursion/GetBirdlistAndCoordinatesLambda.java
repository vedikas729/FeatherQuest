package dev.vedikasharma.birdingexcursion;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetBirdlistAndCoordinatesLambda implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    
    private final APIClient apiClient = new APIClient(); // Your existing APIClient class
    
    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        String address = (String) input.get("address");
        Double radius = Double.valueOf((String) input.get("radius"));
        HashMap<String, Object> response = new HashMap<>();
        
        try {
            // Fix address using getAddress()
            String fixedAddress = apiClient.getAddress(address);
            
            // Get coordinates from fixed address using geocode()
            Coordinate coordinate = apiClient.geocode(fixedAddress);
            
            if (coordinate != null) {
                // Fetch bird list using getNearbyObs()
                ArrayList<Observation> obsList = apiClient.getNearbyObs(coordinate, radius * 1.60934);
                HashMap<String, String> birdList = new HashMap<String, String>();
                for(Observation o : obsList) {
                    birdList.put(o.speciesCode(), o.comName());
                }
                
                // Build response
                response.put("birdList", birdList);
                response.put("coordinate", coordinate);
            } else {
                response.put("error", "Failed to geocode the address");
            }
        } catch (Exception e) {
            response.put("error", "Exception: " + e.getMessage());
        }

        return response;
    }
}
