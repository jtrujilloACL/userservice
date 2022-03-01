package io.getarrays.userservice.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.domain.SoftSkill;
import io.getarrays.userservice.repository.SoftSkillRepository;
import io.getarrays.userservice.service.SoftSkillService;
import lombok.RequiredArgsConstructor;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
@Service @RequiredArgsConstructor @Transactional
public class SoftSkillServiceImplementation implements SoftSkillService{
	
	private final SoftSkillRepository softSkillRepository;
	private final ProfileServiceImplementation profileService;
	
	@Override
	public SoftSkill findByName(String name) {
		return softSkillRepository.findByName(name);
	}

	@Override
	public Optional<SoftSkill> findById(Long id) {
		return softSkillRepository.findById(id);
	}

	@Override
	public List<SoftSkill> findAll() {
		return softSkillRepository.findAll();
	}

	@Override
	public SoftSkill save(SoftSkill softSkill) {
		return softSkillRepository.save(softSkill);
	}

	@Override
	public void deleteById(Long id) {
		softSkillRepository.deleteById(id);
	}

	@Override
	public void addSoftSkillToProfile(String name, String identityDocument) {
		SoftSkill softSkill = softSkillRepository.findByName(name);
		Profile profile = profileService.findByIdentityDocument(identityDocument);
		//profile.getSoftSkill().add(softSkill);		
	}
	

}
