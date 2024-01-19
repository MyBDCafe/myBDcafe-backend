package com.web.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.CafeEvent;

public interface EventRepositoryCustom {

	Page<CafeEvent> findEvent(Pageable pageable, String groupName, String charactorName, Date startDate, Date endDate);
}
