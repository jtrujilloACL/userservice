package io.getarrays.userservice.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.repository.EvaluationRepository;
import io.getarrays.userservice.repository.entity.Evaluation;
import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class EvaluationServiceImplementation implements EvaluationService{
	
	private final EvaluationRepository evaluationRepository;
	
	private final ProfileServiceImplementation profileService;
	
	@Override
	public Evaluation findByTitle(String name) {
		return evaluationRepository.findByTitle(name);
	}

	@Override
	public Optional<Evaluation> findById(Long id) {
		return evaluationRepository.findById(id);
	}

	@Override
	public List<Evaluation> findAll() {
		return evaluationRepository.findAll();
	}

	@Override
	public Evaluation save(Evaluation evaluation) {
		log.info("Save new Evalutation with Title: {} to the database",evaluation.getTitle());
		return evaluationRepository.save(evaluation);
	}

	@Override
	public void deleteById(Long id) {
		evaluationRepository.deleteById(id);
	}

	@Override
	public void addEvaluationToProfile(Long id, String identityDocument) {
		Evaluation evaluation = evaluationRepository.getById(id);
		Profile profile = profileService.findByIdentityDocument(identityDocument);
		//profile.getEvaluation().add(evaluation);
	}

}
