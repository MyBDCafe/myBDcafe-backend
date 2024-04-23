package com.web.service;

import java.util.Optional;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.domain.CafeEvent;
import com.web.dto.EventDto;
import com.web.repository.EventRepository;

public class UrlService {
	
	@Autowired
	EventRepository eRepo;
	
	@Autowired
	private StringEncryptor encryptor;
	
	//이벤트 공유 URL
	public String createURL(Long id) {
		System.out.println(id);
		String encrptId = encryptor.encrypt(String.valueOf(id)).replaceAll("/", "_");
		String url = "http://localhost:8080/shareEvent/"+encrptId;
		return url;
	}

	
	//URL로 이벤트 불러오기
	public EventDto getEvent(String encryptedId) {
		String decryptedId = encryptor.decrypt(encryptedId.replaceAll("_", "/"));
        Long eventId = Long.parseLong(decryptedId);
        
        Optional<CafeEvent> eventOptional = eRepo.findById(eventId);
        if (eventOptional.isPresent()) {
            CafeEvent event = eventOptional.get();
            return new EventDto(event);
        } else {
            return null;
        }
	}

}
