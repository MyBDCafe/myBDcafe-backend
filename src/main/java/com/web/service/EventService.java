package com.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.web.dto.UpdateEventDto;
import com.web.dto.UpdateHoursDto;
import com.web.dto.UpdateLocationDto;
import com.web.repository.BusinessHoursRepository;
import com.web.repository.CharactorRepository;
import com.web.repository.EventRepository;
import com.web.repository.GroupRepository;
import com.web.repository.LocationRepository;

@Service
@Transactional
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
	
	//mainCharacter 등록
	public Charactor findCharactor(String genre, String mainCharacter) {
		List<Group> existingGroup = gRepo.findByGroupName(genre);
		Group group;
		
		System.out.println(existingGroup);
		if(!existingGroup.isEmpty()) {
			group = existingGroup.get(0);
		}else {
			group = Group.builder()
					.groupName(genre)
					.build();
			gRepo.save(group);
		}
		
		Optional<Charactor> existingCharactor = Optional.ofNullable(cRepo.findByCharactorName(mainCharacter));
		
		Charactor charactor;
		if(existingCharactor.isPresent()) {
			charactor = existingCharactor.get();
		}else {
			charactor = Charactor.builder()
                    .charactorName(mainCharacter)
                    .group(group)
                    .build();
			
			cRepo.save(charactor);
		}
		
		
		
		return charactor;
	}
	
	//이벤트 장소 등록
	public Location findLocation(LocationDto loDto) {
		
		if(loDto == null) {
			return null;
		}
		
		Optional<String> latitude = Optional.ofNullable(loDto.getLatitude());
	    
	    if (!latitude.isPresent()) {
	        return null;
	    }

	    Location location = Location.builder()
	                                .latitude(loDto.getLatitude())
	                                .longitude(loDto.getLongitude())
	                                .build();
	    lRepo.save(location);
	    
	    return location;
		
	}
	
	//이벤트 등록
	public void registerEvent(EventDto eventDto) {
		
		Charactor charactor = findCharactor(eventDto.getGenre(), eventDto.getMainCharacter());
		Location location = findLocation(eventDto.getLocation());
		
		List<HoursDto> hoursDto = eventDto.getBusinessHours();
		
		CafeEvent event = CafeEvent.builder()
				.eventName(eventDto.getEventName())
				.charactor(charactor)
				.startDate(eventDto.getStartDate())
				.endDate(eventDto.getEndDate())
				.location(location)
				.eventUrl(eventDto.getEventUrl())
				.memo(eventDto.getMemo())
				.build();
		
		eRepo.save(event);
		
		for(HoursDto hour : hoursDto) {
			BusinessHours businessHour = BusinessHours.builder()
					.cafeEvent(event)
					.Day(hour.getDay())
					.openTime(hour.getOpenTime())
					.closeTime(hour.getCloseTime())
					.build();
			bRepo.save(businessHour);
		}
		
	}

	//이벤트 검색
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
	
	//Id로 이벤트 검색
		public UpdateEventDto findEventFromId(Long id) {
			CafeEvent event = eRepo.findById(id).get();
			UpdateEventDto eventDto = new UpdateEventDto(event); 
			
			return eventDto;
		}
		
	//이벤트 장소 수정
			public Location updateLocation(UpdateLocationDto loDto) {
				
				if(loDto == null) {
					return null;
				}
				
				Optional<String> latitude = Optional.ofNullable(loDto.getLatitude());
			    
			    if (!latitude.isPresent()) {
			        return null;
			    }

			    Location location = Location.builder()
			    							.id(loDto.getId())
			                                .latitude(loDto.getLatitude())
			                                .longitude(loDto.getLongitude())
			                                .build();
			    lRepo.save(location);
			    
			    return location;
				
			}	

	//이벤트 수정
	public void updateEvent(UpdateEventDto eventDto) {
		
		Charactor charactor = findCharactor(eventDto.getGenre(), eventDto.getMainCharacter());
		Location location = updateLocation(eventDto.getLocation());
		List<UpdateHoursDto> hoursDto = eventDto.getBusinessHours();
		
		CafeEvent event = CafeEvent.builder()
				.id(eventDto.getEventId())
				.eventName(eventDto.getEventName())
				.charactor(charactor)
				.startDate(eventDto.getStartDate())
				.endDate(eventDto.getEndDate())
				.location(location)
				.eventUrl(eventDto.getEventUrl())
				.memo(eventDto.getMemo())
				.build();
		
		eRepo.save(event);
		
		for(UpdateHoursDto hour : hoursDto) {
			BusinessHours businessHour = BusinessHours.builder()
					.cafeEvent(event)
					.id(hour.getId())
					.Day(hour.getDay())
					.openTime(hour.getOpenTime())
					.closeTime(hour.getCloseTime())
					.build();
			bRepo.save(businessHour);
		}
	}
	
	//이벤트 삭제
	public void deleteEvent(Long id) {
		eRepo.deleteById(id);
		lRepo.deleteByCafeEventId(id);
	}
	
}
