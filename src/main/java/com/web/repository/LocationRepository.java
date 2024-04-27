package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Long>{

	void deleteByCafeEventId(Long id);

}
