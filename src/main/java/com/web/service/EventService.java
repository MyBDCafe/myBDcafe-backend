package com.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.BusinessHours;
import com.web.domain.CafeEvent;
import com.web.domain.Charactor;
import com.web.domain.Group;
import com.web.domain.Location;
import com.web.dto.EventDto;
import com.web.dto.EventPageDto;
import com.web.dto.HoursDto;
import com.web.dto.LocationDto;
import com.web.repository.BusinessHoursRepository;
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
	
	@Autowired
	BusinessHoursRepository bRepo;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private StringEncryptor encryptor;
	
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
		LocationDto locationDto = eventDto.getLocation();
		if (locationDto != null) {
		    Optional<String> latitude = Optional.ofNullable(locationDto.getLatitude());
		    location.setLatitude(locationDto.getLatitude());
		    location.setLongitude(locationDto.getLongitude());
		    
		    if (latitude.isPresent()) {
		        lRepo.save(location);
		    }
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
		
		List<HoursDto> hoursDto = eventDto.getBusinessHours();
		
		for(HoursDto hour : hoursDto) {
			BusinessHours businessHour = new BusinessHours();
			businessHour.setDay(hour.getDay());
			businessHour.setStartTime(hour.getOpenTime());
			businessHour.setEndTime(hour.getCloseTime());
			businessHour.setCafeEvent(event);
			bRepo.save(businessHour);
		}
		
		
	}

	//이벤트 검색
	@Transactional
	public EventPageDto findEvent(Pageable pageable, String groupName, String charactorName, String s, String e) throws ParseException{
		
		if(s != null && e == null) {
			e = s;
		}else if(e != null && s == null) {
			s = e;
		}
		
	    Date startDate = (s != null) ? dateFormat.parse(s) : null;
	    System.out.println(startDate);
	    Date endDate = (e != null) ? addOneDay(dateFormat.parse(e)) : null;
	    System.out.println(endDate);
		
		
		Page<CafeEvent> events = eRepo.findEvent(pageable, groupName, charactorName, startDate, endDate);
		
		List<EventDto> eventDto = events.stream()
				.map(ev -> new EventDto(ev))
				.collect(Collectors.toList());
		
		EventPageDto eventPages = new EventPageDto();
		eventPages.setContent(eventDto);
		eventPages.setCount(events.getTotalElements());
		eventPages.setSize(events.getSize());
		eventPages.setPage(events.getNumber());
		
		return eventPages;
	}
	
	private Date addOneDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    return calendar.getTime();
	}
	
	
	//이벤트 공유 URL
	public String createURL(Long id) {
		System.out.println(id);
		String encrptId = encryptor.encrypt(String.valueOf(id)).replaceAll("/", "_");
		String url = "http://localhost:8080/shareEvent/"+encrptId;
		return url;
	}

	public EventDto getEvent(String encryptedId) {
		String decryptedId = encryptor.decrypt(encryptedId.replaceAll("_", "/"));
        Long eventId = Long.parseLong(decryptedId);
        
        Optional<CafeEvent> eventOptional = eRepo.findById(eventId);
        if (eventOptional.isPresent()) {
            CafeEvent event = eventOptional.get();
            return new EventDto(event);
        } else {
            return null;
        }
	}
	
}
