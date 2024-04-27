package com.web.service;

import java.util.Optional;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.CafeEvent;
import com.web.dto.EventDto;
import com.web.dto.UrlDto;
import com.web.repository.EventRepository;

@Service
public class UrlService {
	
	@Autowired
	EventRepository eRepo;
	
	@Autowired
	private StringEncryptor encryptor;
	
	//이벤트 공유 URL
	public UrlDto createURL(Long id) {
		System.out.println(id);
		String encrptId = encryptor.encrypt(String.valueOf(id)).replaceAll("/", "_");
		UrlDto urlDto = new UrlDto(encrptId);
//		String url = "http://localhost:8080/shareEvent/"+encrptId;
		return urlDto;
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
