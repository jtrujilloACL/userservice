package io.getarrays.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Evaluation {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String date;
	
	private String title;
		
	private String observation;
	
	private Integer score;
}
