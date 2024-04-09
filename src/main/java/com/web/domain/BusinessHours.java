package com.web.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="BUSINESS_HOURS")
public class BusinessHours {
	
	@Id @Column(name="BUSINESS_HOURS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name="EVENT_ID")
	private CafeEvent cafeEvent;
	
	@Temporal(TemporalType.DATE)
	private Date Day;
	
	@Temporal(TemporalType.TIME)
	private LocalTime openTime;
	@Temporal(TemporalType.TIME)
	private LocalTime closeTime;

	
	

}
