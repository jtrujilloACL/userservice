package io.getarrays.userservice.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.repository.ProfileRepository;
import io.getarrays.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ProfileServiceImplementation implements ProfileService{
	
	private final ProfileRepository profileRepository;
	
	@Override
	public Profile findByIdentityDocument(String identityDocument) {
		return profileRepository.findByIdentityDocument(identityDocument);
	}

	@Override
	public Optional<Profile> findById(Long id) {
		return profileRepository.findById(id);
	}

	@Override
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	@Override
	public Profile save(Profile profile) {
		log.info("Save new profile {} to the database", profile.getName());
		return profileRepository.save(profile);
	}
	
	@Override
	public void deleteById(Long id) {
		profileRepository.deleteById(id);		
	}

	@Override
	public void addUserToProfile(String username, Long id) {

	}
		

}
