package io.getarrays.userservice.service;


import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.repository.entity.Role;

public interface RoleService {
	
	public Role findByName(String name);
	
	public Optional<Role> findById(Long id);
	
	public List<Role> findAll();
	
	public Role save(Role role);
	
	public void deleteById(Long id);
	
	public void addRoleToUser(String username, String roleName);
}
