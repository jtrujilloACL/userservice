package io.getarrays.userservice.domain;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JeanTrujillo
 * @version 1.1
 * @since 16/10/2021 / 22/02/2022
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name; //TODO: Move to entity Profile
	private String username;
	private String password;
	
	@ManyToMany(fetch= FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profileId")
	private Profile profile;
}
