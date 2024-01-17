package com.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Id @Column(name="EVENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String eventName;
	
	@OneToMany(mappedBy = "cafeTeam")
	List<EventCharactor> eventCharacters = new ArrayList<EventCharactor>();
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Temporal(TemporalType.TIME)
	private Date openTime;
	@Temporal(TemporalType.TIME)
	private Date closeTime;
	
	private String eventLocation;
	private String eventUrl;
	private String memo;
	
	

}
