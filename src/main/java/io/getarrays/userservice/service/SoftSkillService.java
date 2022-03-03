package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.repository.entity.SoftSkill;

public interface SoftSkillService {
	public SoftSkill findByName(String name);
	
	public Optional<SoftSkill> findById(Long id);
	
	public List<SoftSkill> findAll();
	
	public SoftSkill save(SoftSkill softSkill);
	
	public void deleteById(Long id);
	
	public void addSoftSkillToProfile(String name, String identityDocument );

}
