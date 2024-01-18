package com.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.web.domain.CafeEvent;
import com.web.domain.Charactor;
import com.web.domain.Group;
import com.web.repository.CharactorRepository;
import com.web.repository.EventRepository;
import com.web.repository.GroupRepository;

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
	
	
	@Test
	public void test() {
//		eManager.clear();
//		Group group = new Group();
//		group.setId(1L);
//		group.setGroupName("PLAVE");
//		gRepo.save(group);
		
//		System.out.println(gRepo.findByGroupName("PLAVE"));
		
//		Charactor charactor = new Charactor();
//		charactor.setId(2L);
//		charactor.setCharactorName("한노아");
//		charactor.setGroup(group);
//		cRepo.save(charactor);

		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		startDate.set(2024, 1, 2);
		endDate.set(2024, 1, 4);
		
		Date sd = startDate.getTime();
		Date ed = endDate.getTime();
//	
//		CafeEvent event = new CafeEvent();
//		event.setEventName("Noah`s Health Club ㅣ PLAVE 노아 생일카페");
//		event.setCharactor(charactor);
//		event.setStartDate(sd);
//		event.setEndDate(ed);
//		event.setEventUrl("https://twitter.com/noah_health0210?s=20");
//		eRepo.save(event);
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<CafeEvent> page = eRepo.findEvent(pageable, null, null, sd, null);
		System.out.println(page.getContent());
	}

}
