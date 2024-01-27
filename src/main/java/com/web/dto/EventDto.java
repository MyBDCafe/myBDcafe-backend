package com.web.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EventDto{
	
	private String eventName;
	private String mainCharacter;
	private String genre;
	private Date startDate;
	private Date endDate;
	private List<LocationDto> location;
	private List<Hours> businessHours;
	private String eventUrl;
	private String memo;
	
}

@Data
class Hours{
	private Date day;
	private Date startTime;
	private Date endTime;
}

@Data
class LocationDto{
	private String latitude;
	private String longitude; 
}
