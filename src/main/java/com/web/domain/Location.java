package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name = "EVENT_LOCATION_SEQ_GENERATOR",sequenceName = "EVENT_LOCATION_SEQ", allocationSize = 1)
@Table(name="EVENT_LOCATION")
public class Location {

	@Id @Column(name="LOCATION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_LOCATION_SEQ_GENERATOR")
	private Long id;
	
	@OneToOne(mappedBy = "location", fetch=FetchType.LAZY) @JsonIgnore
	private CafeEvent cafeEvent;
	
	private String latitude;
	private String longitude;
	
	
	
}
