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
	
	@Column(nullable = true)
	private String identityDocument;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String lastName;
	
	@Column(nullable = true)
	private String genre;
	
	@Column(nullable = true)
	private String email;
	
	@Column(nullable = true)
	private String address;
	
	@Column(nullable = true)
	private String phoneNumber;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User userId;
	
	public Profile(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
