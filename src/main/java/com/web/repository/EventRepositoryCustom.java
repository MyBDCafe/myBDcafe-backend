package com.web.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.CafeEvent;

public interface EventRepositoryCustom {

	Page<CafeEvent> findBySearchOption(Pageable pageable, String groupName, String eventCharactor, Date startDate, Date endDate);
}
