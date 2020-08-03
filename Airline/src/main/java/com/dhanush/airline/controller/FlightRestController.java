package com.dhanush.airline.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.airline.entity.Flight;
import com.dhanush.airline.service.FlightService;
import com.dhanush.airline.vo.SearchCriteria;

@RestController
public class FlightRestController {

	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "/searchFlights", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Flight> searchFlights(@RequestBody SearchCriteria searchcriteria) {
		LocalDate flightDate = Instant.ofEpochMilli(searchcriteria.getFlightDate().getTime())
				.atZone(ZoneId.systemDefault()).toLocalDate();
		List<Flight> flights = flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(
				searchcriteria.getOrigin(), searchcriteria.getDestination(), flightDate, searchcriteria.getCount());
		return flights;
	}

	@RequestMapping(value = "/getFlightByNumberFlightDateFlightTime", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Flight getFlightByNumberFlightDateFlightTime(@RequestBody Flight flight) {
		System.out.println(flight.getFlightNumber());
		System.out.println(flight.getFlightDate());
		System.out.println(flight.getFlightTime());
		return flightService.findByFlightNumberAndFlightDateAndFlightTime(flight.getFlightNumber(),
				flight.getFlightDate(), flight.getFlightTime());
	}

}
