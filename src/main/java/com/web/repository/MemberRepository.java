package com.web.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

	Optional<Member> findByUserEmail(String userEmail);

	
}
