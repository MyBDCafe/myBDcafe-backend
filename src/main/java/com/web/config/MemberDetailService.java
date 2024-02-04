package com.web.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.repository.MemberRepository;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository mainRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Member> optional = mainRepo.findByUserEmail(userEmail);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(userEmail+" 이/가 존재하지 않습니다.");
		}else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}

}