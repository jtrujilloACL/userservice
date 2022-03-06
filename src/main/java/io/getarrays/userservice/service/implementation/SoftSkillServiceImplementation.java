package io.getarrays.userservice.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.dto.SoftSkillDTO;
import io.getarrays.userservice.repository.SoftSkillRepository;
import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.repository.entity.SoftSkill;
import io.getarrays.userservice.service.SoftSkillService;
import lombok.RequiredArgsConstructor;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SoftSkillServiceImplementation implements SoftSkillService {

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
	public void deleteById(Long id) {
		softSkillRepository.deleteById(id);
	}

	@Override
	public SoftSkill save(SoftSkillDTO softSkillDTO) {
		//TODO: Class Map Structur
		SoftSkill skill = new SoftSkill();
		skill.setName(softSkillDTO.getName());
		skill.setDescription(softSkillDTO.getDescription());
		
		SoftSkill skillSave = softSkillRepository.save(skill);
		
		Optional<Profile> profile = profileService.findById(softSkillDTO.getProfileId());
		if( profile.isPresent() ) {
			profile.get().getSoftSkill().add(skill);
			profileService.save(profile.get());
		}		
		return skillSave;
	}

	@Override
	public SoftSkill update(SoftSkillDTO softSkillDTO) {
		// TODO: Class Map Structur
		SoftSkill  skill = new SoftSkill();
		skill.setName(softSkillDTO.getName());
		skill.setDescription(softSkillDTO.getDescription());
		return softSkillRepository.save(skill);
	}
}
