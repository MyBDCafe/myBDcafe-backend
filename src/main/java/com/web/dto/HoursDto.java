package com.web.dto;

import java.util.Date;

import com.web.domain.BusinessHours;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HoursDto{
	private Date day;
	private Date openTime;
	private Date closeTime;
	
	public HoursDto(BusinessHours businessHour) {
		day = businessHour.getDay();
		openTime = businessHour.getStartTime();
		closeTime = businessHour.getEndTime();
	}
}
