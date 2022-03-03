package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.repository.entity.ProgrammingSkill;


public interface ProgrammingSkillService {
	public ProgrammingSkill findByName(String name);
	
	public Optional<ProgrammingSkill> findById(Long id);
	
	public List<ProgrammingSkill> findAll();
	
	public ProgrammingSkill save(ProgrammingSkill programmingSkill);
	
	public void deleteById(Long id);
	
	public void addProgrammingSkillToProfile(String name, String identityDocument );

}
