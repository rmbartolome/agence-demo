package com.rmbartolome.agence.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="workers")
public class Worker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String license;

	private boolean enabled;
}
