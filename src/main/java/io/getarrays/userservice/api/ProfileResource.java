package io.getarrays.userservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.service.ProfileServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 23/02/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
@Slf4j
public class ProfileResource {
	
	private final ProfileServiceImplementation profileService;
	
	@GetMapping("/save")
	public Profile saveProfile(Profile profile) {
		log.info("Save a new profile intendity document: {} to Username: {}",profile.getIdentityDocument(), "username");
		return profileService.saveProfile(profile);
	}
}
