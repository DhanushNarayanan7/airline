package com.dhanush.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.airline.dao.AirlineInfoDao;
import com.dhanush.airline.entity.AirlineInfo;

@Service
public class AirlineInfoService {
	
	@Autowired
	private AirlineInfoDao airlineInfoDao;
	

	public AirlineInfo findByNameOfAirline(String name) {
		return airlineInfoDao.findByNameOfAirline(name);
	}

}
