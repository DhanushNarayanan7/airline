package com.dhanush.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String homepage() {
		return "searchFlights";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/bookFlight")
	public String bookFlight() {
		return "bookFlight";
	}
	

	@GetMapping("/checkin")
	public String checkinPage() {
		return "checkin";
	}

}
