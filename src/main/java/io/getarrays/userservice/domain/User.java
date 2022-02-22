package io.getarrays.userservice.domain;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/10/2021
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	
	@ManyToMany(fetch= FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();

}
