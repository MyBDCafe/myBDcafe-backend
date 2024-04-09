package com.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.web.controller.eventController;
import com.web.domain.CafeEvent;
import com.web.domain.Charactor;
import com.web.domain.Group;
import com.web.repository.CharactorRepository;
import com.web.repository.EventRepository;
import com.web.repository.GroupRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class EventTest {
	
	@Autowired
	EventRepository eRepo;
	
	@Autowired
	EntityManager eManager;
	
	@Autowired
	GroupRepository gRepo;
	
	@Autowired
	CharactorRepository cRepo;
	
	@Autowired
	eventController eController;
	
	
	@Test
	@Transactional
	public void test() throws ParseException {
//		eManager.clear();
//		Group group = new Group();
//		group.setId(2L);
//		group.setGroupName("데못죽");
//		gRepo.save(group);
//		
////		System.out.println(gRepo.findByGroupName("PLAVE"));
//		
//		Charactor charactor = new Charactor();
//		charactor.setId(2L);
//		charactor.setCharactorName("배세진");
//		charactor.setGroup(group);
////		cRepo.save(charactor);
//
//		
//		SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
//		Date start = format.parse("24.02.10");
//		Date end = format.parse("24.02.10");
////	
//		CafeEvent event = new CafeEvent();
//		event.setEventName("𝐓𝐡𝐞 𝐛𝐞𝐠𝐢𝐧𝐧𝐢𝐧𝐠(더 비기닝) : PLAVE 한노아 생일카페");
//		event.setCharactor(charactor);
//		eRvent.setStartDate(start);
//		event.setEndDate(end);
//		event.setEventUrl("https://x.com/beginning0210?s=20");
////		eRepo.save(event);
//		
		Pageable pageable = PageRequest.of(0, 10);
		System.out.println(eController.findEvent(pageable, "테스트", null, null, null));
		
	}

}
