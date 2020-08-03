package com.dhanush.airline.vo;

import java.util.Date;


public class SearchCriteria {
	
	private String origin;
	
	private String destination;
	
	private Date flightDate;
	
	private int count;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public SearchCriteria(String origin, String destination, Date flightDate, int count) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightDate = flightDate;
		this.count = count;
	}

	@Override
	public String toString() {
		return "SearchCriteria [origin=" + origin + ", destination=" + destination + ", flightDate=" + flightDate
				+ ", count=" + count + "]";
	}
	
	

}
