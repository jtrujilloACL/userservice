package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.domain.Profile;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/02/2022
 */
public interface ProfileService {

	public Profile findByIdentityDocument(String identityDocument);
	
	public Optional<Profile> findById(Long id);
	
	public List<Profile> findAll();

	public Profile save(Profile profile);
	
	public void deleteById(Long id);
	
	public void addUserToProfile(String username, Long id);

}
