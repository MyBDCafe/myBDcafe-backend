package com.web.dto;

import java.util.List;
import lombok.Data;

@Data
public class EventPageDto {
	private List<EventDto> content;
	private Long count;
	private int size;
	private int page;
	
}
