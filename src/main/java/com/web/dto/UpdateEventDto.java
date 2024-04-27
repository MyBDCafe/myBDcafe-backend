package com.web.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.web.domain.CafeEvent;
import com.web.domain.Location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateEventDto {
	
	@NotNull
	private Long eventId;
	
	@NotBlank
	private String eventName;
	
	private String mainCharacter;
	private String genre;
	private Date startDate;
	private Date endDate;
	private UpdateLocationDto location;
	private List<UpdateHoursDto> businessHours;
	private String eventUrl;
	private String memo;
	
	public UpdateEventDto(CafeEvent event) {
		eventId = event.getId();
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
		    location = new UpdateLocationDto(locationFromEvent);
		} else {
		    location = null;
		}
		businessHours = event.getBusinessHours().stream()
				.map(e -> new UpdateHoursDto(e))
				.collect(Collectors.toList());
		eventUrl = event.getEventUrl();
		memo = event.getMemo();
	}
}
