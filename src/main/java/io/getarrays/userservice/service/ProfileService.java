package io.getarrays.userservice.service;

import java.util.List;

import io.getarrays.userservice.domain.Profile;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/02/2022
 */
public interface ProfileService {

	Profile saveProfile(Profile profile);
	Profile getProfile(Long id);
	List<Profile> getProfiles();
	
	Profile findByIndentityDocument(String identityDocument);
}
