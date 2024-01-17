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
@Table(name="CHARACTOR")
public class Charactor {

	@Id @Column(name="CHARACTOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String charactorName;
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
	
}
