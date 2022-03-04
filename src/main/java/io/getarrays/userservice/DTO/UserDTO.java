package io.getarrays.userservice.dto;

import lombok.Data;

@Data 
public class UserDTO {
	private String username;
	private String password;
	
	public UserDTO(String username,String password){
		super();
		this.username=username;
		this.password=password;
	}
}
