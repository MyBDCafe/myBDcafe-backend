package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CAFE_EVENT")
public class CafeEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventNum;
	
	private String eventName;
	private String eventCharactor;
	
	private String groupName;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private String eventLocation;
	private String eventUrl;
	
	

}
