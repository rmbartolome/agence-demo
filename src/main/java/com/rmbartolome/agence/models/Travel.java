package com.rmbartolome.agence.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name="travels")
@JsonIgnoreProperties(value = {"startDate", "finishDate"},
		allowGetters = true)
public class Travel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "worker_id")
	private Worker usuarioId;
	
	@OneToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicleId;

	@Column(name="finish_date")
	private Date finishDate;
		    
    @Column(name="start_date",nullable=false)
	private Date startDate;
}