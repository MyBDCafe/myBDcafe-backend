package com.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.dto.EventDto;
import com.web.dto.EventPageDto;
import com.web.service.EventService;

@RestController
public class eventController {
	
	@Autowired
	EventService eService;
	
	@PostMapping("events")
	public void registerEvent(@RequestBody @Validated EventDto eventDto) {
		eService.registerEvent(eventDto);
	}
	
	@GetMapping("search")
	public EventPageDto findEvent(Pageable pageable, String g, String c, String s, String e) throws ParseException{
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		


		return eService.findEvent(pageable, g, c, s, e);
	}
	

}


