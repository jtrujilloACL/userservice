package io.getarrays.userservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

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

import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.ProfileService;
import io.getarrays.userservice.service.UserService;
import io.getarrays.userservice.utils.UtilToken;
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
public class ProfileController {
	
	@Autowired
	private final ProfileService profileService;
	
	@Autowired
	private final UserService userService;
		
	@PostMapping("/save")
	public ResponseEntity<Profile> saveProfile(HttpServletRequest request, @RequestBody Profile profile) {
		//TODO Validate if exist Profile for this user
		String username = UtilToken.getUsernameToToken(request);
		User user = userService.getUser(username);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		log.info("Save a new profile intendity document: {} to Username: {}",profile.getIdentityDocument(), user);
		profile.setUserId(user);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/profile/save").toString() );
		return ResponseEntity.created(uri).body( profileService.save(profile) );
	}
	
	@GetMapping("/all")
	public List<Profile> getProfiles(){
		return StreamSupport
				.stream(profileService.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProfile(@RequestBody Profile profileDetail, @PathVariable(value = "id") Long profileId) {
		Optional<Profile> profile = profileService.findById(profileId);

		if (!profile.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		profile.get().setIdentityDocument( profileDetail.getIdentityDocument());
		profile.get().setName(profileDetail.getName());
		profile.get().setLastName(profileDetail.getLastName());
		profile.get().setGenre(profileDetail.getGenre());
		profile.get().setEmail(profileDetail.getEmail());
		profile.get().setAddress(profileDetail.getAddress());
		profile.get().setPhoneNumber(profileDetail.getPhoneNumber());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(profile.get()));
	}
	
	@DeleteMapping("/{id}")
	//TODO: soft delete
	public ResponseEntity<?> deleteRole(@PathVariable(value="id") Long id ){
		if (!profileService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		profileService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
