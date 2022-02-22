package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.userservice.domain.Role;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/10/2021
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
