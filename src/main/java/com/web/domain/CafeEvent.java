package com.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "CAFE_EVENT_SEQ_GENERATOR",sequenceName = "CAFE_EVENT_SEQ", allocationSize = 1)
@Table(name = "CAFE_EVENT")
public class CafeEvent {
	
	@Id @Column(name="EVENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAFE_EVENT_SEQ_GENERATOR")
	private Long id;
	
	private String eventName;
	
	@ManyToOne
	@JoinColumn(name="CHARACTOR_ID")
	private Charactor charactor;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@OneToMany(mappedBy = "cafeEvent", fetch = FetchType.EAGER)
	private List<BusinessHours> businessHours = new ArrayList<>();
	
	
	private String eventUrl;
	private String memo;
	
	

}
