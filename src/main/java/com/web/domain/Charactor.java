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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SequenceGenerator(name = "CHARACTOR_SEQ_GENERATOR", sequenceName = "CHARACTOR_SEQ", allocationSize = 1)
@Table(name="CHARACTOR")
public class Charactor {

	@Id @Column(name="CHARACTOR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHARACTOR_SEQ_GENERATOR")
	private Long id;
	
	@Column(unique = true)
	private String charactorName;
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
	@Builder
	public void updateCharactorName(String charactorName, Group group) {
		this.charactorName = charactorName;
		this.group = group;
	}
}
