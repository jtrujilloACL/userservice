package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.getarrays.userservice.repository.entity.User;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/02/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
}
