package io.getarrays.userservice.service;

import java.util.List;
import java.util.Optional;

import io.getarrays.userservice.dto.EvaluationDTO;
import io.getarrays.userservice.repository.entity.Evaluation;


public interface EvaluationService {
	public Evaluation findByTitle(String name);
	
	public Optional<Evaluation> findById(Long id);
	
	public List<Evaluation> findAll();
	
	public Evaluation save(EvaluationDTO evaluationDTO);
	
	public void deleteById(Long id);
	
	Evaluation update(EvaluationDTO evaluationDTO, Long evalId);

}
