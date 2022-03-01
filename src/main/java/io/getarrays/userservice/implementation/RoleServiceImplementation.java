package io.getarrays.userservice.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.repository.RoleRepository;
import io.getarrays.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImplementation implements RoleService{
	

	private final RoleRepository roleRepository;

	private final UserServiceImplementation userService;
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role save(Role role) {
		log.info("Save new Role: {} to the database",role.getName());
		return roleRepository.save(role);
	}

	@Override
	public Optional<Role> findById(Long id) {
		log.info("Get Role for id: {}",id);
		return roleRepository.findById(id);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = userService.getUser(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}

}
