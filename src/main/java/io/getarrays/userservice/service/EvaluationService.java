package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.domain.Evaluation;


public interface EvaluationService {
	public Evaluation findByTitle(String name);
	
	public Optional<Evaluation> findById(Long id);
	
	public List<Evaluation> findAll();
	
	public Evaluation save(Evaluation evaluation);
	
	public void deleteById(Long id);
	
	public void addEvaluationToProfile(Long id, String identityDocument );

}
