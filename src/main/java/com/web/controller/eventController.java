package com.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.CafeEvent;
import com.web.service.EventService;

@RestController
public class eventController {
	
	@Autowired
	EventService eService;
	
	@RequestMapping("search")
	public Page<CafeEvent> findEvent(Pageable pageable, String g, String c, Date s, Date e){
		return eService.findEvent(pageable, g, c, s, e);
	}

}
