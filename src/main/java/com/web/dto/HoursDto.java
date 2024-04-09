package com.web.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.web.domain.BusinessHours;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HoursDto{
	private Date day;
	private LocalTime openTime;
	private LocalTime closeTime;
	
	public HoursDto(BusinessHours businessHour) {
		day = businessHour.getDay();
		openTime = businessHour.getOpenTime();
		closeTime = businessHour.getCloseTime();
	}
}
