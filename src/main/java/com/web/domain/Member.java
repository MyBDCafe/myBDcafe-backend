package com.web.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name="member")
public class Member {
	
	@Id @Column(name="MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userEmail;
	private String password;
	private Role role;

}
