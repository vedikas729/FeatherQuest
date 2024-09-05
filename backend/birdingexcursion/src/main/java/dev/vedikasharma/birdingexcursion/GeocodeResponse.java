package dev.vedikasharma.birdingexcursion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocodeResponse {
	private List<Results> results;
	
	@JsonProperty("error_message")
	private String errorMessage;
	
	private String status;

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	
	public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    // Getter and Setter
    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    
    public static class Results {
    	
    	private List<AddressComponent> address_components;
        private String formatted_address;
        private Geometry geometry;
        private String place_id;
        private List<String> types;
        
        
        public List<AddressComponent> getAddress_components() {
			return address_components;
		}

		public void setAddress_components(List<AddressComponent> address_components) {
			this.address_components = address_components;
		}

		public String getFormatted_address() {
			return formatted_address;
		}

		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}

		public String getPlace_id() {
			return place_id;
		}

		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}

		public List<String> getTypes() {
			return types;
		}

		public void setTypes(List<String> types) {
			this.types = types;
		}
		
		public Geometry getGeometry() {
        	return geometry;
        }

		public void setGeometry(Geometry geometry) {
			this.geometry = geometry;
		}
        
    }
    
    public static class AddressComponent {
    	private String long_name;
    	private String short_name;
    	private List<String> types;
		
    	public String getLong_name() {
			return long_name;
		}
		public void setLong_name(String long_name) {
			this.long_name = long_name;
		}
		public String getShort_name() {
			return short_name;
		}
		public void setShort_name(String short_name) {
			this.short_name = short_name;
		}
		public List<String> getTypes() {
			return types;
		}
		public void setTypes(List<String> types) {
			this.types = types;
		}
    	
    }

    public static class Geometry {
        private Bounds bounds;
        private Location location;
        private String location_type;
        private Viewport viewport;
		public Bounds getBounds() {
			return bounds;
		}
		public void setBounds(Bounds bounds) {
			this.bounds = bounds;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public String getLocation_type() {
			return location_type;
		}
		public void setLocation_type(String location_type) {
			this.location_type = location_type;
		}
		public Viewport getViewport() {
			return viewport;
		}
		public void setViewport(Viewport viewport) {
			this.viewport = viewport;
		}
        

    }
    
    public static class Bounds {
    	private Location northeast;
    	private Location southwest;
		
    	public Location getNortheast() {
			return northeast;
		}
		public void setNortheast(Location northeast) {
			this.northeast = northeast;
		}
		public Location getSouthwest() {
			return southwest;
		}
		public void setSouthwest(Location southwest) {
			this.southwest = southwest;
		}
    	
    }
    
    public static class Viewport {
    	private Location northeast;
    	private Location southwest;
		
    	public Location getNortheast() {
			return northeast;
		}
		public void setNortheast(Location northeast) {
			this.northeast = northeast;
		}
		public Location getSouthwest() {
			return southwest;
		}
		public void setSouthwest(Location southwest) {
			this.southwest = southwest;
		}
    	
    }

    public static class Location {
        private double lat;
		private double lng;

        public double getLat() {
        	return lat;
        }
        
        public double getLng() {
        	return lng;
        }

		public void setLat(double lat) {
			this.lat = lat;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}
    }

    // Other classes (Bounds, AddressComponent, etc.) should also be defined similarly
}

