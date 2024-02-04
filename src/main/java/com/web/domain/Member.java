package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",sequenceName = "MEMBER_SEQ", allocationSize = 1)
public class Member {
	
	@Id @Column(name="MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
	
	private String userEmail;
	private String password;
	private Role role;

}
