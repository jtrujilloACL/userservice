package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.getarrays.userservice.repository.entity.Profile;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 22/08/2022
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	Profile findByIdentityDocument(String identityDocument);
}
