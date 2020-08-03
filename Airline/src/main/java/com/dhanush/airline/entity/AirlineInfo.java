package com.dhanush.airline.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airlineInfo")
public class AirlineInfo {
	
	@Id
	@GeneratedValue
	private long airlineId;
	
	private String airlineLogo;

	private String nameOfAirline;
	
	public AirlineInfo() {
		
	}
	

	public AirlineInfo(long airlineId, String airlineLogo, String nameOfAirline) {
		super();
		this.airlineId = airlineId;
		this.airlineLogo = airlineLogo;
		this.nameOfAirline = nameOfAirline;
	}


	public AirlineInfo(String airlineLogo, String nameOfAirline) {
		super();
		this.airlineLogo = airlineLogo;
		this.nameOfAirline = nameOfAirline;
	}


	public long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(long airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineLogo() {
		return airlineLogo;
	}

	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}

	public String getNameOfAirline() {
		return nameOfAirline;
	}

	public void setNameOfAirline(String nameOfAirline) {
		this.nameOfAirline = nameOfAirline;
	}

	@Override
	public String toString() {
		return "AirlineInfo [airlineId=" + airlineId + ", airlineLogo=" + airlineLogo + ", nameOfAirline="
				+ nameOfAirline + "]";
	}
	
}