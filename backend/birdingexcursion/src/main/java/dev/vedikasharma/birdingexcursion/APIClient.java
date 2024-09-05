package dev.vedikasharma.birdingexcursion;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vedikasharma.birdingexcursion.GeocodeResponse.Location;

public class APIClient {

	private final String BASE_URL_EBIRD = "https://api.ebird.org/v2/data/obs/geo/recent";
	private final String BASE_URL_GMAPS = "https://maps.googleapis.com/maps/api";
	private final String EBIRD_API_KEY = "8j1gj86486vs";
	private final String GOOGLEPLATFORM_API_KEY = "AIzaSyAxZpxnJo9pXdk-RBYJGGUhpe1szm8nOOs";
	private final HttpClient client;
	private final ObjectMapper objectMapper;

	public APIClient() {
		client = HttpClient.newHttpClient();
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	
	public Coordinate geocode(String address) throws IOException, InterruptedException {
		String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL_GMAPS + "/geocode/json?address=" + encodedAddress 
						+ "&key=" + GOOGLEPLATFORM_API_KEY))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		GeocodeResponse geocodeResponse = objectMapper.readValue(response.body(), GeocodeResponse.class);
		
		if ("OK".equals(geocodeResponse.getStatus()) && !geocodeResponse.getResults().isEmpty()) {
			Location location = geocodeResponse.getResults().get(0).getGeometry().getLocation();
			
			return new Coordinate(location.getLat(), location.getLng());
		} else {
			System.err.println("Error: " + geocodeResponse.getErrorMessage() + "\nStatus: " + geocodeResponse.getStatus());
			return null;
		}
		
	}
	
	
	public ArrayList<Observation> getNearbyObs(Coordinate coord, double dist) throws IOException, InterruptedException {
		double lat = coord.lat();
		double lng = coord.lng();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL_EBIRD + "?lat=" + lat + "&lng=" + lng 
						+ "&hotspot=true" + "&back=30" + "&dist=" + dist))
				.header("x-ebirdapitoken", EBIRD_API_KEY)
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return objectMapper.readValue(response.body(), new TypeReference<>() {});
	}
	
	
	public ArrayList<Observation> getNearbyObsBySpecies(String speciesCode, Coordinate coord, 
			double dist) throws IOException, InterruptedException {
		double lat = coord.lat();
		double lng = coord.lng();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL_EBIRD + "/" + speciesCode + "?lat=" + lat + "&lng=" + lng 
						+ "&hotspot=true" + "&back=30" + "&dist=" + dist))
				.header("x-ebirdapitoken", EBIRD_API_KEY)
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return objectMapper.readValue(response.body(), new TypeReference<>() {});
	}
	
	
	
	public HashMap<String, Object> getDirections(Coordinate origin, HashMap<String, Observation> places, ArrayList<String> s) throws IOException, InterruptedException {		
		HashMap<String, Object> h = new HashMap<String, Object>();
		
		String c = origin.lat() + "," + origin.lng();
		
		StringBuilder addedPart = new StringBuilder();
		addedPart.append("origin=").append(URLEncoder.encode(c, StandardCharsets.UTF_8));
	    addedPart.append("&destination=").append(URLEncoder.encode(c, StandardCharsets.UTF_8));
	    addedPart.append("&waypoints=optimize:true");
	    
		for(int i = 0; i < s.size(); i++) {
			String coordinate = places.get(s.get(i)).lat() + "," + places.get(s.get(i)).lng();
			addedPart.append(URLEncoder.encode("|" + coordinate, StandardCharsets.UTF_8));
		}
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL_GMAPS + "/directions/json?" + addedPart
						+ "&key=" + GOOGLEPLATFORM_API_KEY))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		DirectionsResponse directionsResponse = objectMapper.readValue(response.body(), DirectionsResponse.class);
				
		List<Integer> order = directionsResponse.getRoutes().get(0).getWaypoint_order();
		h.put("order", order);
		
		String finalURL = "https://www.google.com/maps/dir/?api=1&";
		
		String addedPart2 = "destination=" + c + "&origin=" + c + "&waypoints=";
		for(int i = 0; i < order.size(); i++) {
			String key = s.get(order.get(i));
			String coordinate = places.get(key).lat() + "," + places.get(key).lng();
			addedPart2 += coordinate;
			if(i != order.size() - 1) { 
				addedPart2 += "|";
			}
		}
		
		h.put("directions", finalURL + addedPart2);
		
		return h;
	}
	
	
	public String getAddress(String badAddress) throws IOException, InterruptedException {
		String url = "https://addressvalidation.googleapis.com/v1:validateAddress?key=" + GOOGLEPLATFORM_API_KEY;

		String requestBody = String.format("""
				{
				"address": {
					"locality": "",
					"addressLines": ["%s"]
					}
				}
				""", badAddress);

		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(url))
		        .header("Content-Type", "application/json")
		        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
		        .build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		AddressVerificationResponse addressVerificationResponse = objectMapper.readValue(response.body(), AddressVerificationResponse.class);
		
		return addressVerificationResponse.getResult().getAddress().getFormattedAddress();
	}

}
