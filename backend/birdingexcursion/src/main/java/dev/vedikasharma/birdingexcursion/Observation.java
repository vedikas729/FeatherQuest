package dev.vedikasharma.birdingexcursion;

public record Observation(String speciesCode, String comName, String sciName, String locId, 
		String locName, String obsDt, int howMany, double lat, double lng, boolean obsValid, 
		boolean obsReviewed, boolean locationPrivate, String subId) {


	public String speciesCode() {
		return speciesCode;
	}

	public String comName() {
		return comName;
	}

	public String sciName() {
		return sciName;
	}

	public String locId() {
		return locId;
	}

	public String locName() {
		return locName;
	}

	public String obsDt() {
		return obsDt;
	}

	public int howMany() {
		return howMany;
	}

	public double lat() {
		return lat;
	}

	public double lng() {
		return lng;
	}

	public boolean obsValid() {
		return obsValid;
	}

	public boolean obsReviewed() {
		return obsReviewed;
	}

	public boolean locationPrivate() {
		return locationPrivate;
	}

	public String subId() {
		return subId;
	}

}
