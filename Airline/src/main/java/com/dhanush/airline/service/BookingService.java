package com.dhanush.airline.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhanush.airline.dao.BookingDao;
import com.dhanush.airline.entity.BookingRecord;
import com.dhanush.airline.entity.Checkin;
import com.dhanush.airline.entity.Flight;
import com.dhanush.airline.entity.Passenger;
import com.dhanush.airline.entity.User;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private UserService userService;

	public List<BookingRecord> findAll() {
		return bookingDao.findAll();
	}

	// 2.7
	@Transactional
	public BookingRecord bookFlight(BookingRecord record) {

		List<Passenger> passengersList = new ArrayList<>();

		record.getPassengers().forEach(passenger -> {
			if (!StringUtils.isEmpty(passenger.getFirstName()) && !StringUtils.isEmpty(passenger.getLastName())
					&& !StringUtils.isEmpty(passenger.getGender())
					&& !StringUtils.isEmpty(passenger.getMobileNumber())) {
				passengersList.add(passenger);
				// passenger.setBookingId(bookingRecordFromDB.getBookingId());
				// passengerService.save(passenger);
			}
		});

		BookingRecord bookingRecord = new BookingRecord(LocalDateTime.now(), record.getDestination(), record.getFare(),
				record.getFlightDate(), record.getFlightNumber(), record.getFlightTime(), record.getOrigin(), "Booked",
				passengersList);
		BookingRecord bookingRecordFromDB = bookingDao.save(bookingRecord);

		passengersList.forEach(passenger -> {
			passenger.setBookingId(bookingRecordFromDB.getBookingId());
			passenger.setCheckIn(new Checkin(null, null));
			passengerService.save(passenger);
		});

		// TODO exception if bookingRecord is not available

		// TODO exception if passenger is not available

		// TODO exception if flight is not available

		// TODO exception if flightService is not able to update inventory

		Flight flight = flightService.findByFlightNumberAndFlightDateAndFlightTime(record.getFlightNumber(),
				record.getFlightDate(), record.getFlightTime());
		int count = flight.getInventory().getCount();
		flight.getInventory().setCount(count - record.getPassengers().size());
		flightService.saveFlight(flight);
		return bookingRecord;
	}

	public List<BookingRecord> findBookingRecordByUserId(String userName) {
		User user = userService.findByUserName(userName);
		System.out.println(user.getId());
		return bookingDao.getBookingRecordsByUserId(user.getId());
	}

	public BookingRecord getBookingById(long bookingId) {
		return bookingDao.findByBookingId(bookingId);
	}

}
