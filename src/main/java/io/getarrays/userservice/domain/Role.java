package io.getarrays.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/10/2021
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;	
}
