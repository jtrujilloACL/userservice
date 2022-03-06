package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.dto.ProgrammingSkillDTO;
import io.getarrays.userservice.repository.entity.ProgrammingSkill;


public interface ProgrammingSkillService {
	public ProgrammingSkill findByName(String name);
	
	public Optional<ProgrammingSkill> findById(Long id);
	
	public List<ProgrammingSkill> findAll();
	
	public void deleteById(Long id);
	
	public ProgrammingSkill save(ProgrammingSkillDTO programmingSkillDTO);
	
	public ProgrammingSkill update(ProgrammingSkillDTO programmingSkillDTO);
}
