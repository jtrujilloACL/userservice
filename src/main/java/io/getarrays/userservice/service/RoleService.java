package io.getarrays.userservice.service;


import java.util.List;

import io.getarrays.userservice.domain.Role;

public interface RoleService {
	Role saveRole(Role role); 	//Create
	Role getRole(Long id);		//Read
	Role updateRole(Role role); //Update
	void deleteRole(Long id);   //Delete
	
	List<Role> getRoles();		//Read All roles
	void addRoleToUser(String username, Long id);
}
