package dev.vedikasharma.birdingexcursion;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.*;

public class GetItineraryLambda implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    
    private final APIClient apiClient = new APIClient(); // Your existing APIClient class

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        @SuppressWarnings("unchecked")
		ArrayList<String> chosenBirds = (ArrayList<String>) input.get("chosenBirds");
        HashMap<String, Object> c = (HashMap<String, Object>) input.get("coordinate");
        Coordinate coordinate = new Coordinate((Double) c.get("lat"), (Double) c.get("lng"));
        Double radius = Double.valueOf((String) input.get("radius"));
        HashMap<String, Observation> birdLocations = new HashMap<>();
        StringBuilder textBody = new StringBuilder();
        HashMap<String, Object> response = new HashMap<>();

        try {
            // Step 1: Get nearby observation for each bird species
        	for(int s = 0; s < chosenBirds.size(); s++) {
                ArrayList<Observation> observations = apiClient.getNearbyObsBySpecies(chosenBirds.get(s), coordinate, radius * 1.60934);
                int max = 0;
    			Observation maxObs = observations.get(0);
    			for(int i = 0; i < observations.size(); i++) {
    				if(observations.get(i).howMany() > max) {
    					max = observations.get(i).howMany();
    					maxObs = observations.get(i);
    				}
    			}
    				
    			birdLocations.put(chosenBirds.get(s), maxObs);
    				    			
    		}

            // Step 2: Get the Google Maps link
            HashMap<String, Object> dir = apiClient.getDirections(coordinate, birdLocations, chosenBirds);

            // Step 3: Generate the itinerary text
            @SuppressWarnings("unchecked")
			List<Integer> order = (List<Integer>) dir.get("order"); // Get from getDirections()
            for (int i = 0; i < order.size(); i++) {
                String bird = chosenBirds.get(order.get(i));
                Observation location = birdLocations.get(bird);

                textBody.append("Stop ").append((char) ('A' + i)).append(": ").append(location.locName()).append("\n");
                textBody.append("Bird to look for: ").append(location.comName()).append("\n\n");
            }

            // Build response
            response.put("itinerary", textBody.toString());
            response.put("googleMapsLink", dir.get("directions"));
            
        } catch (Exception e) {
            response.put("error", "Exception: " + e.getMessage());
        }


        return response;
    }
}
