package com.web.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.CafeEvent;
import com.web.domain.Charactor;
import com.web.domain.Group;
import com.web.domain.Location;
import com.web.dto.EventDto;
import com.web.dto.EventPageDto;
import com.web.repository.CharactorRepository;
import com.web.repository.EventRepository;
import com.web.repository.GroupRepository;
import com.web.repository.LocationRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eRepo;
	
	@Autowired
	GroupRepository gRepo;
	
	@Autowired
	CharactorRepository cRepo;
	
	@Autowired
	LocationRepository lRepo;
	
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
		
		Location location = new Location();
		String latitude = eventDto.getLocation().getLatitude();
		location.setLatitude(eventDto.getLocation().getLatitude());
		location.setLongitude(eventDto.getLocation().getLongitude());
		if(!latitude.isEmpty()) {
			lRepo.save(location);
		}
		
		CafeEvent event = new CafeEvent();
		event.setEventName(eventDto.getEventName());
		event.setCharactor(charactor);
		event.setStartDate(eventDto.getStartDate());
		event.setEndDate(eventDto.getEndDate());
		event.setLocation(location);
		event.setEventUrl(eventDto.getEventUrl());
		event.setMemo(eventDto.getMemo());
		
		eRepo.save(event);
		
		
	}

	//이벤트 검색
	@Transactional
	public EventPageDto findEvent(Pageable pageable, String groupName, String charactorName, Date startDate, Date endDate){
		if(startDate != null && endDate == null) {
			endDate = startDate;
		}else if(endDate != null && startDate == null) {
			startDate = endDate;
		}
		Page<CafeEvent> events = eRepo.findEvent(pageable, groupName, charactorName, startDate, endDate);
		
		List<EventDto> eventDto = events.stream()
				.map(e -> new EventDto(e))
				.collect(Collectors.toList());
		
		EventPageDto eventPages = new EventPageDto();
		eventPages.setContent(eventDto);
		eventPages.setCount(events.getTotalElements());
		eventPages.setSize(events.getSize());
		eventPages.setPage(events.getNumber());
		
		return eventPages;
	}
	
}
