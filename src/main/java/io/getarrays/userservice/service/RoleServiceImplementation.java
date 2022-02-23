package io.getarrays.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImplementation implements RoleService{
	
	private final RoleRepository roleRepositorio;
	
	@Override
	public Role saveRole(Role role) {
		log.info("Save new Role: {} to the database",role.getName());
		return roleRepositorio.save(role);
	}

	@Override
	public Role getRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleToUser(String username, Long id) {
		// TODO Auto-generated method stub
		
	}

}
