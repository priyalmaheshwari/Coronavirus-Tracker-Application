package com.example.demo.models;

public class LocationStats {

	private String state;
	private String country;
	private int LatestTotalcases;
	private int difffrompreviousday;
	public int getDifffrompreviousday() {
		return difffrompreviousday;
	}
	public void setDifffrompreviousday(int difffrompreviousday) {
		this.difffrompreviousday = difffrompreviousday;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalcases() {
		return LatestTotalcases;
	}
	public void setLatestTotalcases(int latestTotalcases) {
		LatestTotalcases = latestTotalcases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", LatestTotalcases=" + LatestTotalcases
				+ ", difffrompreviousday=" + difffrompreviousday + "]";
	}
	
	
}
