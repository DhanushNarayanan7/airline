package com.dhanush.airline.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.airline.dao.FlightDao;
import com.dhanush.airline.entity.Flight;

@Service
public class FlightService {

	@Autowired
	private FlightDao flightDao;

	// 2.1
	public List<Flight> findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(String name, LocalDate flightDate) {
		return flightDao.findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(name, flightDate);
	}

	// 2.2
	public List<Flight> findByOriginAndFlightDate(String origin, LocalDate flightDate) {
		return flightDao.findByOriginAndFlightDate(origin, flightDate);
	}

	// 2.3
	public List<Flight> getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(String origin, String destination,
			LocalDate flightDate) {
		return flightDao.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, flightDate);
	}

	// 2.3(a)
	public List<Flight> getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(String origin, String destination,
			LocalDate flightDate, int numberOfPassenger) {
		return flightDao.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, flightDate,
				numberOfPassenger);
	}

	// 2.4
	public List<Flight> findByFlightNumberAndOriginAndDestination(String flightNumber, String origin,
			String destination) {
		return flightDao.findByFlightNumberAndOriginAndDestination(flightNumber, origin, destination);
	}

	// 2.5
	public Flight findByFlightNumberAndFlightDateAndFlightTime(String flightNumber, LocalDate flightDate,
			LocalTime flightTime) {
		return flightDao.findByFlightNumberAndFlightDateAndFlightTime(flightNumber, flightDate, flightTime);
	}

	// 2.6 && 2.8
	public List<Flight> saveAll(List<Flight> flights) {
		return flightDao.saveAll(flights);
	}

	// 2.7
	public Flight saveFlight(Flight flight) {
		return flightDao.save(flight);
	}

	// 2.7
	public Flight findByOriginAndDestinationAndFlightDateAndFlightTime(String origin, String destination,
			LocalDate flightDate, LocalTime flightTime) {
		return flightDao.findByOriginAndDestinationAndFlightDateAndFlightTime(origin, destination, flightDate,
				flightTime);
	}
	
	//Test
	public List<Flight> findAll(){
		return flightDao.findAll();
	}


}
