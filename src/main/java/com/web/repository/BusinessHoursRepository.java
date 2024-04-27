package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.BusinessHours;

public interface BusinessHoursRepository extends CrudRepository<BusinessHours, Long> {

	void deleteByCafeEventId(Long eventId);

}
