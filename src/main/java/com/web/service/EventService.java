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

	Page<CafeEvent> findBySearchOption(Pageable pageable, String groupName, String eventCharactor, Date startDate, Date endDate){
		return eRepo.findBySearchOption(pageable, groupName, eventCharactor, startDate, endDate);
	}
}
