package io.getarrays.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ProfileServiceImplementation implements ProfileService{
	
	private final ProfileRepository profileRepository;
	
	@Override
	public Profile saveProfile(Profile profile) {
		log.info("Save new profile {} to the database", profile.getName());
		return profileRepository.save(profile);
	}
		
	@Override
	public Profile getProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Profile> getProfiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Profile findByIndentityDocument(String identityDocument) {
		// TODO Auto-generated method stub
		return null;
	}

}
