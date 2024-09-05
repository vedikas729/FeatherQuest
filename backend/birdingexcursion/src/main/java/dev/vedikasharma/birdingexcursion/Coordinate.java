package dev.vedikasharma.birdingexcursion;

public record Coordinate(double lat, double lng) {

	public double lat() {
		return lat;
	}

	public double lng() {
		return lng;
	}

}
