package com.dhanush.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.airline.entity.BookingRecord;
import com.dhanush.airline.service.BookingService;

@RestController
public class BookingRestController {
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("/allBookings")
	public List<BookingRecord> findAll(){
		return bookingService.findAll();
	}
	
	@PostMapping("/bookFlight")
	public BookingRecord bookFlight(@RequestBody BookingRecord bookingRecord) {	
		return bookingService.bookFlight(bookingRecord);
	}
	
	@GetMapping("/booking/{id}")
	public  BookingRecord getBookingById(@PathVariable long id) {
		System.out.println("id"+id);
		return bookingService.getBookingById(id);
	}
	
	

}
