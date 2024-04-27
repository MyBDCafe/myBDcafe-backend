package com.web.dto;

import lombok.Data;

@Data
public class UrlDto {
	private String url;
	
	public UrlDto(String encrptId) {
		this.url = "http://43.201.105.59:8080/shareEvent/"+encrptId;
	}

}
