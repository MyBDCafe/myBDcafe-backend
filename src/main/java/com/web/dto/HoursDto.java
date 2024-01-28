package com.web.dto;

import java.util.Date;

import com.web.domain.BusinessHours;

import lombok.Data;

@Data
public class HoursDto{
	private Date day;
	private Date startTime;
	private Date endTime;
	
	public HoursDto(BusinessHours businessHour) {
		day = businessHour.getDay();
		startTime = businessHour.getStartTime();
		endTime = businessHour.getEndTime();
	}
}
