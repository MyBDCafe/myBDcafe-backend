package com.web.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CHARACTOR")
public class Charactor {

	@Id @Column(name="CHARACTOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String charactorName;
	
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
//	@Builder
//	public void updateCharactorName(String charactorName, Group group) {
//		this.charactorName = charactorName;
//		this.group = group;
//	}
}
