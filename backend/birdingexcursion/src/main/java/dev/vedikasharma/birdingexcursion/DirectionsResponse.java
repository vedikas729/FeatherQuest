package dev.vedikasharma.birdingexcursion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectionsResponse {
    
    public List<GeocodedWaypoint> geocoded_waypoints;
    public List<Route> routes;
    public String status;
    
    @JsonProperty("error_message")
	private String errorMessage;
    

    public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<GeocodedWaypoint> getGeocoded_waypoints() {
		return geocoded_waypoints;
	}

	public void setGeocoded_waypoints(List<GeocodedWaypoint> geocoded_waypoints) {
		this.geocoded_waypoints = geocoded_waypoints;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static class GeocodedWaypoint {
        public String geocoder_status;
        public String place_id;
        public List<String> types;
    }

	public static class Route {
        public Bounds bounds;
        public String copyrights;
        public List<Leg> legs;
        public Polyline overview_polyline;
        public String summary;
        public List<String> warnings;
        public List<Integer> waypoint_order;
        

        public Bounds getBounds() {
			return bounds;
		}

		public void setBounds(Bounds bounds) {
			this.bounds = bounds;
		}

		public String getCopyrights() {
			return copyrights;
		}

		public void setCopyrights(String copyrights) {
			this.copyrights = copyrights;
		}

		public List<Leg> getLegs() {
			return legs;
		}

		public void setLegs(List<Leg> legs) {
			this.legs = legs;
		}

		public Polyline getOverview_polyline() {
			return overview_polyline;
		}

		public void setOverview_polyline(Polyline overview_polyline) {
			this.overview_polyline = overview_polyline;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public List<String> getWarnings() {
			return warnings;
		}

		public void setWarnings(List<String> warnings) {
			this.warnings = warnings;
		}

		public List<Integer> getWaypoint_order() {
			return waypoint_order;
		}

		public void setWaypoint_order(List<Integer> waypoint_order) {
			this.waypoint_order = waypoint_order;
		}

		public static class Bounds {
            public Location northeast;
            public Location southwest;
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

        public static class Leg {
            public Distance distance;
            public Duration duration;
            public String end_address;
            public Location end_location;
            public String start_address;
            public Location start_location;
            public List<Step> steps;
            public List<String> traffic_speed_entry;
            public List<String> via_waypoint;
            

            public Distance getDistance() {
				return distance;
			}

			public void setDistance(Distance distance) {
				this.distance = distance;
			}

			public Duration getDuration() {
				return duration;
			}

			public void setDuration(Duration duration) {
				this.duration = duration;
			}

			public String getEnd_address() {
				return end_address;
			}

			public void setEnd_address(String end_address) {
				this.end_address = end_address;
			}

			public Location getEnd_location() {
				return end_location;
			}

			public void setEnd_location(Location end_location) {
				this.end_location = end_location;
			}

			public String getStart_address() {
				return start_address;
			}

			public void setStart_address(String start_address) {
				this.start_address = start_address;
			}

			public Location getStart_location() {
				return start_location;
			}

			public void setStart_location(Location start_location) {
				this.start_location = start_location;
			}

			public List<Step> getSteps() {
				return steps;
			}

			public void setSteps(List<Step> steps) {
				this.steps = steps;
			}

			public List<String> getTraffic_speed_entry() {
				return traffic_speed_entry;
			}

			public void setTraffic_speed_entry(List<String> traffic_speed_entry) {
				this.traffic_speed_entry = traffic_speed_entry;
			}

			public List<String> getVia_waypoint() {
				return via_waypoint;
			}

			public void setVia_waypoint(List<String> via_waypoint) {
				this.via_waypoint = via_waypoint;
			}

			public static class Distance {
                public String text;
                public int value;
				public String getText() {
					return text;
				}
				public void setText(String text) {
					this.text = text;
				}
				public int getValue() {
					return value;
				}
				public void setValue(int value) {
					this.value = value;
				}
                
            }

            public static class Duration {
                public String text;
                public int value;
				public String getText() {
					return text;
				}
				public void setText(String text) {
					this.text = text;
				}
				public int getValue() {
					return value;
				}
				public void setValue(int value) {
					this.value = value;
				}
                
            }

            public static class Step {
                public Distance distance;
                public Duration duration;
                public Location end_location;
                public String html_instructions;
                public Polyline polyline;
                public Location start_location;
                public String travel_mode;
                public String maneuver;
				public Distance getDistance() {
					return distance;
				}
				public void setDistance(Distance distance) {
					this.distance = distance;
				}
				public Duration getDuration() {
					return duration;
				}
				public void setDuration(Duration duration) {
					this.duration = duration;
				}
				public Location getEnd_location() {
					return end_location;
				}
				public void setEnd_location(Location end_location) {
					this.end_location = end_location;
				}
				public String getHtml_instructions() {
					return html_instructions;
				}
				public void setHtml_instructions(String html_instructions) {
					this.html_instructions = html_instructions;
				}
				public Polyline getPolyline() {
					return polyline;
				}
				public void setPolyline(Polyline polyline) {
					this.polyline = polyline;
				}
				public Location getStart_location() {
					return start_location;
				}
				public void setStart_location(Location start_location) {
					this.start_location = start_location;
				}
				public String getTravel_mode() {
					return travel_mode;
				}
				public void setTravel_mode(String travel_mode) {
					this.travel_mode = travel_mode;
				}
				public String getManeuver() {
					return maneuver;
				}
				public void setManeuver(String maneuver) {
					this.maneuver = maneuver;
				}
                

            }
        }

        public static class Polyline {
            public String points;

			public String getPoints() {
				return points;
			}

			public void setPoints(String points) {
				this.points = points;
			}
            
        }
    }

    public static class Location {
        public double lat;
        public double lng;
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
        
    }
}
