package com.web.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.web.domain.BusinessHours;
import com.web.domain.CafeEvent;
import com.web.domain.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto{
	private String eventName;
	private String mainCharacter;
	private String genre;
	private Date startDate;
	private Date endDate;
	private LocationDto location;
	private List<HoursDto> businessHours;
	private String eventUrl;
	private String memo;
	
	public EventDto(CafeEvent event) {
		eventName = event.getEventName();
		mainCharacter = event.getCharactor().getCharactorName();
        if (event.getCharactor().getGroup() != null) {
            genre = event.getCharactor().getGroup().getGroupName();
        } else {
            genre = null;
        }
		startDate = event.getStartDate();
		endDate = event.getEndDate();
		Location locationFromEvent = event.getLocation(); 
		if (locationFromEvent != null) {
		    location = new LocationDto(locationFromEvent);
		} else {
		    location = null;
		}
		businessHours = event.getBusinessHours().stream()
				.map(e -> new HoursDto(e))
				.collect(Collectors.toList());
		eventUrl = event.getEventUrl();
		memo = event.getMemo();
	}
	
}
