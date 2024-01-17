package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name="EVENT_CHAR")
public class EventCharactor {

	@Id @Column(name="EVENT_CHAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "EVENT_ID")
	private CafeEvent cafeEvent;
	
	@ManyToOne
	@JoinColumn(name = "CHARACTOR_ID")
	private Charactor charactor;
	
	
}
