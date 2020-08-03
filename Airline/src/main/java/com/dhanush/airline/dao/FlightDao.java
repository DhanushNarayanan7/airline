package com.dhanush.airline.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dhanush.airline.entity.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight, Long> {

	// 2.1
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	List<Flight> findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(String name, LocalDate flightDate);

	// 2.2
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	List<Flight> findByOriginAndFlightDate(String origin, LocalDate flightDate);

	// 2.3
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	List<Flight> getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(String origin, String destination,
			LocalDate flightDate);

	// 2.3(a)
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	@Query(value = "from Flight f where f.origin= :origin and f.destination= :destination and f.flightDate= :flightDate and f.inventory.count >= :count order by f.fare.fare")
	List<Flight> getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(String origin, String destination,
			LocalDate flightDate, int count);

	// 2.4
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	List<Flight> findByFlightNumberAndOriginAndDestination(String flightNumber, String origin, String destination);

	// 2.5
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	Flight findByFlightNumberAndFlightDateAndFlightTime(String flightNumber, LocalDate flightDate,
			LocalTime flightTime);

	// 2.7
	@EntityGraph(attributePaths = { "fare", "flightInfo", "inventory", "flightInfo.airlineInfo" })
	Flight findByOriginAndDestinationAndFlightDateAndFlightTime(String origin, String destination, LocalDate flightDate,
			LocalTime flightTime);
	
}
