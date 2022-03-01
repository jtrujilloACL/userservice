	package io.getarrays.userservice.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.domain.ProgrammingSkill;
import io.getarrays.userservice.repository.ProgrammingSkillRepository;
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
	public ProgrammingSkill save(ProgrammingSkill programmingSkill) {
		return programmingSkillRepository.save(programmingSkill);
	}

	@Override
	public void deleteById(Long id) {
		programmingSkillRepository.deleteById(id);		
	}

	@Override
	public void addProgrammingSkillToProfile(String name, String identityDocument) {
		ProgrammingSkill programmingSkill = programmingSkillRepository.findByName(name);
		Profile profile = profileService.findByIdentityDocument(identityDocument);
		//profile.getProgrammingSkill().add(programmingSkill);
	}

}
