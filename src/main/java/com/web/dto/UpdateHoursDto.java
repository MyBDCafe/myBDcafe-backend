package com.web.dto;

import java.time.LocalTime;
import java.util.Date;

import com.web.domain.BusinessHours;
import com.web.domain.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateHoursDto {
	private Long id;
	private Date day;
	private LocalTime openTime;
	private LocalTime closeTime;
	
	public UpdateHoursDto(BusinessHours businessHour) {
		id = businessHour.getId();
		day = businessHour.getDay();
		openTime = businessHour.getOpenTime();
		closeTime = businessHour.getCloseTime();
	}
}
