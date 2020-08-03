package com.dhanush.airline.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookingRecord")
public class BookingRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3103984818331012750L;

	@Id
	@GeneratedValue
	private long bookingId;

	private LocalDateTime bookingDate;

	private String destination;

	private double fare;

	private LocalDate flightDate;

	private String flightNumber;

	private LocalTime flightTime;

	private String origin;

	private String status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookingDetails", joinColumns = {@JoinColumn(name="bookingId")} , inverseJoinColumns = {@JoinColumn(name="passengerId")})
	private List<Passenger> passengers;
	
	public BookingRecord() {
		
	}

	public BookingRecord(LocalDateTime bookingDate,String destination, double fare, LocalDate flightDate,
			String flightNumber, LocalTime flightTime, String origin, String status, List<Passenger> passengers) {
		super();
		this.bookingDate = bookingDate;
		this.destination = destination;
		this.fare = fare;
		this.flightDate = flightDate;
		this.flightNumber = flightNumber;
		this.flightTime = flightTime;
		this.origin = origin;
		this.status = status;
		this.passengers = passengers;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalTime getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(LocalTime flightTime) {
		this.flightTime = flightTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}
	
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
		

	@Override
	public String toString() {
		return "BookingRecord [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", destination=" + destination
				+ ", fare=" + fare + ", flightDate=" + flightDate + ", flightNumber=" + flightNumber + ", flightTime="
				+ flightTime + ", origin=" + origin + ", status=" + status + ", passengers=" + passengers +  "]";
	}

	

}
