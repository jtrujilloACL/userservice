package io.getarrays.userservice.repository.entity;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JeanTrujillo
 * @version 1.1
 * @since 16/10/2021 / 23/02/2022
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@ManyToMany(fetch= FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
}
