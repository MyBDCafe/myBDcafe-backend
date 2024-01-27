package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name = "BUSINESS_HOURS_SEQ_GENERATOR",sequenceName = "BUSINESS_HOURS_SEQ", allocationSize = 1)
@Table(name="BUSINESS_HOURS")
public class BusinessHours {
	
	@Id @Column(name="BUSINESS_HOURS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESS_HOURS_SEQ_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID")
	private CafeEvent cafeEvent;
	
	@Temporal(TemporalType.DATE)
	private Date Day;
	
	@Temporal(TemporalType.DATE)
	private Date startTime;
	@Temporal(TemporalType.DATE)
	private Date endTime;
	
	

}
