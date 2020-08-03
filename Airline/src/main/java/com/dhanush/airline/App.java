package com.dhanush.airline;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Query;

import com.dhanush.airline.dao.AirlineInfoDao;
import com.dhanush.airline.dao.FlightDao;
import com.dhanush.airline.entity.AirlineInfo;
import com.dhanush.airline.entity.BookingRecord;
import com.dhanush.airline.entity.Fare;
import com.dhanush.airline.entity.Flight;
import com.dhanush.airline.entity.FlightInfo;
import com.dhanush.airline.entity.Inventory;
import com.dhanush.airline.entity.Passenger;
import com.dhanush.airline.service.AirlineInfoService;
import com.dhanush.airline.service.BookingService;
import com.dhanush.airline.service.FlightService;
import com.dhanush.airline.service.PassengerService;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws ParseException {

		ApplicationContext ac = SpringApplication.run(App.class, args);
		FlightService flightService = ac.getBean(FlightService.class);
		BookingService bookingService = ac.getBean(BookingService.class);
		PassengerService passengerService = ac.getBean(PassengerService.class);
		AirlineInfoService airlineInfoService = ac.getBean(AirlineInfoService.class);
		/*
		System.out.println("--Assessment 2.1---Fetch Indigo Flights on 21/8/2020--");
		flightService.findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate("INDIGO", LocalDate.of(2020, 8, 21))
				.forEach(flight -> {
					System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
							+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin()
							+ " Destination: " + flight.getDestination() + " AirineName: "
							+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline());
				});

		System.out.println("--Assessment 2.2---Flights leaving from Delhi on 21/8/2020--");
		flightService.findByOriginAndFlightDate("DELHI", LocalDate.of(2020, 8, 21)).forEach(flight -> {
			System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
					+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin() + " Destination: "
					+ flight.getDestination() + " AirineName: "
					+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline());
		});

		System.out.println(
				"--Assessment 2.3---Flights leaving from Delhi to Chennai on 21/8/2020 and fare low to high--");
		flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare("DELHI", "CHENNAI",
				LocalDate.of(2020, 8, 21)).forEach(flight -> {
					System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
							+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin()
							+ " Destination: " + flight.getDestination() + " AirineName: "
							+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline() + " Fare: "
							+ flight.getFare().getFare() + " Inventory Count: " + flight.getInventory().getCount());
				});

		System.out.println(
				"--Assessment 2.3 (a)---Flights leaving from Delhi to Chennai on 21/8/2020 and fare low to high and pass passenger and return flights only inventory is above passenger count--");
		System.out.println("----Passing passenger count as 110----");
		flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare("DELHI", "CHENNAI",
				LocalDate.of(2020, 8, 21), 110).forEach(flight -> {
					System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
							+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin()
							+ " Destination: " + flight.getDestination() + " AirineName: "
							+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline() + " Fare: "
							+ flight.getFare().getFare() + " Inventory Count: " + flight.getInventory().getCount());
				});

		System.out.println(
				"--Assessment 2.3 (a)---Flights leaving from Delhi to Chennai on 21/8/2020 and fare low to high and pass passenger and return flights only inventory is above passenger count--");
		System.out.println("----Passing passenger count as 500(Negative Scenario)----");
		flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare("DELHI", "CHENNAI",
				LocalDate.of(2020, 8, 21), 500).forEach(flight -> {
					System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
							+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin()
							+ " Destination: " + flight.getDestination() + " AirineName: "
							+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline() + " Fare: "
							+ flight.getFare().getFare() + " Inventory Count: " + flight.getInventory().getCount());
				});

		System.out.println("--Assessment 2.4---Flights with combination of flightNumber and origin and destination--");
		flightService.findByFlightNumberAndOriginAndDestination("6E-6684", "CHENNAI", "PORTBLAIR").forEach(flight -> {
			System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate()
					+ " FlightNumber: " + flight.getFlightNumber() + " Origin: " + flight.getOrigin() + " Destination: "
					+ flight.getDestination() + " AirineName: "
					+ flight.getFlightInfo().getAirlineInfo().getNameOfAirline() + " Fare: "
					+ flight.getFare().getFare() + " Inventory Count: " + flight.getInventory().getCount());
		});

		System.out.println("--Assessment 2.5---Single Flight with combination of flightNumber and flightDate and flightNumber--");
		Flight flight = flightService.findByFlightNumberAndFlightDateAndFlightTime("SG-434", LocalDate.of(2020, 8, 21),
				LocalTime.of(11, 45, 00));
		System.out.println("Flight ID: " + flight.getId() + " FlightDate: " + flight.getFlightDate() + " FlightNumber: "
				+ flight.getFlightNumber() + " Origin: " + flight.getOrigin() + " Destination: "
				+ flight.getDestination() + " AirineName: " + flight.getFlightInfo().getAirlineInfo().getNameOfAirline()
				+ " Fare: " + flight.getFare().getFare() + " Inventory Count: " + flight.getInventory().getCount());


		System.out.println("--Assessment 2.6---Scedule 6 additional flights PUNE to CHENNAI on 22-Aug-2020--");
		AirlineInfo airlineInfo = airlineInfoService.findByNameOfAirline("Spicejet");
		Flight flight1 = new Flight("CHENNAI", "1hrs 30mins", LocalDate.of(2020, 8, 22), "SG-101",
				LocalTime.of(01, 00, 00), "PUNE", new Fare("INR", 1000),
				new FlightInfo("SG-101", "Airbus", 200, airlineInfo), new Inventory(500));

		Flight flight2 = new Flight("CHENNAI", "1hrs 10mins", LocalDate.of(2020, 8, 22), "SG-102",
				LocalTime.of(5, 00, 00), "PUNE", new Fare("INR", 1300),
				new FlightInfo("SG-102", "Airbus", 200, airlineInfo), new Inventory(500));
		
		Flight flight3 = new Flight("CHENNAI", "1hrs 05mins", LocalDate.of(2020, 8, 22), "SG-103",
				LocalTime.of(2, 10, 00), "PUNE", new Fare("INR", 900),
				new FlightInfo("SG-103", "Airbus", 200, airlineInfo), new Inventory(500));
		
		Flight flight4 = new Flight("CHENNAI", "2hrs 20mins", LocalDate.of(2020, 8, 22), "SG-104",
				LocalTime.of(3, 15, 00), "PUNE", new Fare("INR", 2000),
				new FlightInfo("SG-104", "Airbus", 200, airlineInfo), new Inventory(500));
		
		Flight flight5 = new Flight("CHENNAI", "3hrs 30mins", LocalDate.of(2020, 8, 22), "SG-105",
				LocalTime.of(5, 40, 00), "PUNE", new Fare("INR", 2300),
				new FlightInfo("SG-105", "Airbus", 200, airlineInfo), new Inventory(500));
		
		Flight flight6 = new Flight("CHENNAI", "2hrs 50mins", LocalDate.of(2020, 8, 22), "SG-106",
				LocalTime.of(10, 45, 00), "PUNE", new Fare("INR", 3000),
				new FlightInfo("SG-106", "Airbus", 200, airlineInfo), new Inventory(500));

		flightService.saveAll(Arrays.asList(flight1, flight2, flight3, flight4, flight5, flight6)).forEach(System.out::println);
		
		
		System.out.println(
				"--Assessment 2.7---Book flight for 2 passengers from DELHI to HYDERABAD on 21-Aug-2020 01:15AM--");		
		bookingService.bookFlight();
		

		
		System.out.println("--Assessment 2.8---Schedule 4 additional flights DELHI to PUNE on 21-Aug-2020--");
		AirlineInfo indigoAirlineInfo = airlineInfoService.findByNameOfAirline("Indigo");
		AirlineInfo airAsiaAirlineInfo = airlineInfoService.findByNameOfAirline("Air Asia");
		AirlineInfo vistaraAirlineInfo = airlineInfoService.findByNameOfAirline("Vistara");
		AirlineInfo airIndiaAirlineInfo = airlineInfoService.findByNameOfAirline("Air India");
		Flight indigoFlight= new Flight("PUNE", "1hrs 30mins", LocalDate.of(2020, 8, 21), "6E-193",
				LocalTime.of(01, 00, 00), "DELHI", new Fare("INR", 1000),
				new FlightInfo("6E-193", "Airbus", 200, indigoAirlineInfo), new Inventory(500));

		Flight airAsiaFlight = new Flight("PUNE", "1hrs 10mins", LocalDate.of(2020, 8, 21), "I5-193",
				LocalTime.of(5, 00, 00), "DELHI", new Fare("INR", 1300),
				new FlightInfo("I5-193", "Airbus", 200, airAsiaAirlineInfo), new Inventory(500));
		
		Flight vistaraFlight = new Flight("PUNE", "1hrs 05mins", LocalDate.of(2020, 8, 21), "UK-193",
				LocalTime.of(2, 10, 00), "DELHI", new Fare("INR", 900),
				new FlightInfo("UK-193", "Airbus", 200, vistaraAirlineInfo), new Inventory(500));
		
		Flight airIndiaFlight = new Flight("PUNE", "2hrs 20mins", LocalDate.of(2020, 8, 21), "AI-193",
				LocalTime.of(3, 15, 00), "DELHI", new Fare("INR", 2000),
				new FlightInfo("AI-193", "Airbus", 200, airIndiaAirlineInfo), new Inventory(500));

		flightService.saveAll(Arrays.asList(indigoFlight, airAsiaFlight, vistaraFlight, airIndiaFlight)).forEach(System.out::println);
		
		System.out.println("--Assessment 2.9---Update 6E-6686 flight date and time--");
		Flight flightUpdateTime = flightService.findByFlightNumberAndFlightDateAndFlightTime("6E-6686", LocalDate.of(2020, 8, 21), LocalTime.of(03, 15));
		flightUpdateTime.setFlightDate(LocalDate.of(2020, 8, 22));
		flightUpdateTime.setFlightTime(LocalTime.of(6, 30));
		flightService.saveFlight(flightUpdateTime);
		System.out.println(flightUpdateTime.toString());
*/
	}
}
