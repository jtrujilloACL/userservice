package io.getarrays.userservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Profile {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String identityDocument;
	
	private String name;
	
	private String lastName;
	
	private String genre;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String address;
	
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User userId;
	/*
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "evaluation_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Evaluation> evaluation = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "programming_skill_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ProgrammingSkill> programmingSkill = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL`)
	@JoinColumn(name = "soft_skill_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<SoftSkill> softSkill = new ArrayList<>();*/
}
