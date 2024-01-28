package com.web.dto;

import com.web.domain.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDto{
	private String latitude;
	private String longitude; 
	
	public LocationDto(Location locationFromEvent) {
		latitude = locationFromEvent.getLatitude();
		longitude = locationFromEvent.getLongitude();
	}
}
