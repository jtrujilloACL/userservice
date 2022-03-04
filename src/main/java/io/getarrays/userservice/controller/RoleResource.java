package io.getarrays.userservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.getarrays.userservice.dto.RoleToUserDTO;
import io.getarrays.userservice.repository.entity.Role;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.implementation.RoleServiceImplementation;
import io.getarrays.userservice.service.implementation.UserServiceImplementation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleResource {

	@Autowired
	private RoleServiceImplementation roleService;
	
	@Autowired
	private UserServiceImplementation userService;
	
	@PostMapping("/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		log.info("Save a new Role: {} to the database",role.getName());
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString() );
		return ResponseEntity.created(uri).body( roleService.save(role) );
	}

	@PostMapping("/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO form){
		log.info("Add Role to User: {} to User {}",form.getRoleName(),form.getUsername() );
		
		Role role = roleService.findByName( form.getRoleName() );
		User user = userService.getUser(form.getUsername());
		
		if( role == null || user == null ){
			return ResponseEntity.notFound().build();
		}
		
		roleService.addRoleToUser( form.getUsername(),form.getRoleName() );
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Role> getRoles(){
		return StreamSupport
				.stream(roleService.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@RequestBody Role roleDetail, @PathVariable(value = "id") Long roleId) {
		Optional<Role> role = roleService.findById(roleId);

		if (!role.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		role.get().setName(roleDetail.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(value="id") Long id ){
		if (!roleService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
