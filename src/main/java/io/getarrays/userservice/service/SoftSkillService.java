package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.dto.SoftSkillDTO;
import io.getarrays.userservice.repository.entity.SoftSkill;

public interface SoftSkillService {
	public SoftSkill findByName(String name);
	
	public Optional<SoftSkill> findById(Long id);
	
	public List<SoftSkill> findAll();

	public void deleteById(Long id);
	
	public SoftSkill save(SoftSkillDTO softSkillDTO);
	
	public SoftSkill update(SoftSkillDTO softSkillDTO);
	
	
}
