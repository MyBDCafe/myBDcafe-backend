package com.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.CafeEvent;
import com.web.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eRepo;

	public Page<CafeEvent> findEvent(Pageable pageable, String groupName, String charactorName, Date startDate, Date endDate){
		
		return eRepo.findEvent(pageable, groupName, charactorName, startDate, endDate);
	}
}
