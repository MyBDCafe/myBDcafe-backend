package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Group;
import java.util.List;
import java.util.Optional;


public interface GroupRepository extends CrudRepository<Group, Long> {
	List<Group> findByGroupName(String groupName);
}
