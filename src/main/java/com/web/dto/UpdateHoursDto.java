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
	private Long hourId;
	private Date day;
	private LocalTime openTime;
	private LocalTime closeTime;
	
	public UpdateHoursDto(BusinessHours businessHour) {
		hourId = businessHour.getId();
		day = businessHour.getDay();
		openTime = businessHour.getOpenTime();
		closeTime = businessHour.getCloseTime();
	}
}
