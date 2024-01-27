package com.web.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.CafeEvent;
import com.web.domain.Charactor;
import com.web.domain.Group;
import com.web.dto.EventDto;
import com.web.dto.EventPageDto;
import com.web.repository.CharactorRepository;
import com.web.repository.EventRepository;
import com.web.repository.GroupRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eRepo;
	
	@Autowired
	GroupRepository gRepo;
	
	@Autowired
	CharactorRepository cRepo;
	
	//이벤트 등록
	public void registerEvent(EventDto eventDto) {
		
		List<Group> existingGroup = gRepo.findByGroupName(eventDto.getGenre());
		Group group = new Group();
		System.out.println(existingGroup);
		if(!existingGroup.isEmpty()) {
			group.setId(existingGroup.get(0).getId());
		}else {
			group.setGroupName(eventDto.getGenre());
			gRepo.save(group);
		}
		
		Optional<Charactor> existingCharactor = Optional.ofNullable(cRepo.findByCharactorName(eventDto.getMainCharacter()));
		Charactor charactor = new Charactor();
		if(existingCharactor.isPresent()) {
			charactor.setId(existingCharactor.get().getId());
			charactor.setCharactorName(existingCharactor.get().getCharactorName());
		}else {
			charactor.setCharactorName(eventDto.getMainCharacter());
			charactor.setGroup(group);
			cRepo.save(charactor);
		}
		
		CafeEvent event = new CafeEvent();
		event.setEventName(eventDto.getEventName());
		event.setCharactor(charactor);
		event.setStartDate(eventDto.getStartDate());
		event.setEndDate(eventDto.getEndDate());
		event.setEventUrl(eventDto.getEventUrl());
		event.setMemo(eventDto.getMemo());
		
		eRepo.save(event);
		
		
	}

	@Transactional
	public EventPageDto findEvent(Pageable pageable, String groupName, String charactorName, Date startDate, Date endDate){
		if(startDate != null && endDate == null) {
			endDate = startDate;
		}else if(endDate != null && startDate == null) {
			startDate = endDate;
		}
		Page<CafeEvent> event = eRepo.findEvent(pageable, groupName, charactorName, startDate, endDate);
		EventPageDto eventPage = new EventPageDto();
		eventPage.setContent(null);
		
		return eventPage;
	}
	
}
