package io.getarrays.userservice.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.dto.ProgrammingSkillDTO;
import io.getarrays.userservice.repository.ProgrammingSkillRepository;
import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.repository.entity.ProgrammingSkill;
import io.getarrays.userservice.service.ProgrammingSkillService;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class ProgrammingSkillServiceImplementation implements ProgrammingSkillService {
	
	private final ProgrammingSkillRepository programmingSkillRepository;
	private final ProfileServiceImplementation profileService;
	
	@Override
	public ProgrammingSkill findByName(String name) {
		return programmingSkillRepository.findByName(name);
	}

	@Override
	public Optional<ProgrammingSkill> findById(Long id) {
		return programmingSkillRepository.findById(id);
	}

	@Override
	public List<ProgrammingSkill> findAll() {
		return programmingSkillRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		programmingSkillRepository.deleteById(id);		
	}

	@Override
	public ProgrammingSkill save(ProgrammingSkillDTO programmingSkillDTO) {
		//TODO: Class Map Structur
		ProgrammingSkill skill = new ProgrammingSkill();
		skill.setName(programmingSkillDTO.getName());
		skill.setLevel(programmingSkillDTO.getLevel());
		skill.setDescription(programmingSkillDTO.getDescription());
		
		ProgrammingSkill skillSave = programmingSkillRepository.save(skill);
		
		Optional<Profile> profile = profileService.findById(programmingSkillDTO.getProfileId());
		if( profile.isPresent() ) {
			profile.get().getProgrammingSkill().add(skill);
			profileService.save(profile.get());
		}		
		return skillSave;
	}

	@Override
	public ProgrammingSkill update(ProgrammingSkillDTO programmingSkillDTO) {
		//TODO: Class Map Structur
		ProgrammingSkill skill = new ProgrammingSkill();
		skill.setName(programmingSkillDTO.getName());
		skill.setLevel(programmingSkillDTO.getLevel());
		skill.setDescription(programmingSkillDTO.getDescription());
				
		return programmingSkillRepository.save(skill);
	}

}
