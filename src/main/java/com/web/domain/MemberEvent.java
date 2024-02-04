package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name = "MEMBER_EVENT_SEQ_GENERATOR",sequenceName = "MEMBER_EVENT_SEQ", allocationSize = 1)
@Table
public class MemberEvent {

	@Id @Column(name="MEMBER_EVENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_EVENT_SEQ_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "EVENT_ID")
	private CafeEvent cafeEvent;
	
}
