package com.rmbartolome.agence.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name="vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
		
	private String model;

	private String trademark;
	
	@Temporal(TemporalType.DATE)	
	@Column(name="date", nullable=false)
	private Date manufacturingDate;
}
