package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.CafeEvent;

public interface EventRepository extends CrudRepository<CafeEvent, Long>, EventRepositoryCustom{
	
}
