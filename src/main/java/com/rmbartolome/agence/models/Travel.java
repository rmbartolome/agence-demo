package com.rmbartolome.agence.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name="travels")
public class Travel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@OneToOne
	private Worker usuarioId;
	
	@OneToOne
	private Vehicle vehicleId;

	@Column(name="finish_date",nullable=false)
	@LastModifiedDate
	private Date finishDate;
		    
    @Column(name="start_date",nullable=false)
	@CreatedDate
	private Date startDate;
}