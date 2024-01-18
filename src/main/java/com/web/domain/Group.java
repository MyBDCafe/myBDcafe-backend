package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "C_GROUP_SEQ_GENERATOR",sequenceName = "C_GROUP_SEQ", allocationSize = 1)
@Table(name="C_GROUP")
public class Group {

	@Id @Column(name="C_GROUP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "C_GROUP_SEQ_GENERATOR")
	private Long id;
	
	@Column(unique = true)
	private String groupName;

}
