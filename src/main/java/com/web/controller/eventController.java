package com.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//등록
	@PostMapping("event/create")
	public void registerEvent(@RequestBody @Validated EventDto eventDto) {
		eService.registerEvent(eventDto);
	}
	
	//검색
	@GetMapping("event/search")
	public EventPageDto findEvent(Pageable pageable, String g, String c, String s, String e) throws ParseException{
		return eService.findEvent(pageable, g, c, s, e);
	}
	
	@PatchMapping("event/update")
	public void updateEvent(@RequestBody EventDto eventDto) {
		eService.updateEvent(eventDto);
	}
	
	@DeleteMapping("event/delete/{eventId}")
	public void deleteEvent(@PathVariable("eventId") Long id) {
		eService.deleteEvent(id);
	}
	
	
	@PostMapping("createURL")
	public String createURL(@RequestBody Map<String, Long> data) {
		Long id = data.get("id");
		return eService.createURL(id);
	}
	
	@GetMapping("shareEvent/{encryptedId}")
	public ResponseEntity<EventDto> getEvent(@PathVariable("encryptedId") String encryptedId) {
		EventDto event = eService.getEvent(encryptedId);
	    if (event != null) {
	        return ResponseEntity.ok(event);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}


