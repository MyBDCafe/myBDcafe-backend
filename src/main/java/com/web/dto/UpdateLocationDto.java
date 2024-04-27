package com.web.dto;

import com.web.domain.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateLocationDto {
	private Long id;
	private String latitude;
	private String longitude; 
	
	public UpdateLocationDto(Location locationFromEvent) {
		id = locationFromEvent.getId();
		latitude = locationFromEvent.getLatitude();
		longitude = locationFromEvent.getLongitude();
	}
}
