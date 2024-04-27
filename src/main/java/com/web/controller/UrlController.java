package com.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.dto.EventDto;
import com.web.dto.UrlDto;
import com.web.service.UrlService;

@RestController
public class UrlController {
	
	@Autowired
	UrlService uService;

	//공유URL 생성
		@PostMapping("createURL")
		public UrlDto createURL(@RequestBody Map<String, Long> data) {
			if(data.isEmpty()) {
				throw new NullPointerException("id 없음");
			}
			
			Long id = data.get("id");
			return uService.createURL(id);
		}
		
		//URL로 이벤트 불러오기
		@GetMapping("shareEvent/{encryptedId}")
		public ResponseEntity<EventDto> getEvent(@PathVariable("encryptedId") String encryptedId) {
			EventDto event = uService.getEvent(encryptedId);
		    if (event != null) {
		        return ResponseEntity.ok(event);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
}
