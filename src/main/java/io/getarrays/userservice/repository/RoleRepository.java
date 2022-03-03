package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.getarrays.userservice.repository.entity.Role;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/02/2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
