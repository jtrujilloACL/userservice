package io.getarrays.userservice.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.getarrays.userservice.dto.EvaluationDTO;
import io.getarrays.userservice.repository.EvaluationRepository;
import io.getarrays.userservice.repository.entity.Evaluation;
import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class EvaluationServiceImplementation implements EvaluationService{
	
	private final EvaluationRepository evaluationRepository;
	private final ProfileServiceImplementation profileService;
	private final UserServiceImplementation userService;
	
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
	public Evaluation save(EvaluationDTO evaluationDTO) {
		log.info("Save new Evalutation with Title: {} to the database",evaluationDTO.getTitle());
		
		log.info("profileId: {}",evaluationDTO.getProfileId());
		Evaluation evaluation = new Evaluation();
		evaluation.setDate(evaluationDTO.getDate());
		evaluation.setObservation(evaluationDTO.getObservation());
		evaluation.setScore(evaluationDTO.getScore());
		evaluation.setTitle(evaluationDTO.getTitle());
		Evaluation evalSave = evaluationRepository.save(evaluation);
		
		Optional<Profile> profile = profileService.findById(evaluationDTO.getProfileId());
		if( profile.isPresent() ) {
			log.info("profileName: {}", profile.get().getName() );
			profile.get().getEvaluation().add(evaluation);
			profileService.save(profile.get());

		}		
		return evalSave;
	}

	@Override
	public Evaluation update(EvaluationDTO evaluationDTO) {
		log.info("Save new Evalutation with Title: {} to the database",evaluationDTO.getTitle());
		
		Evaluation evaluation = new Evaluation();
		evaluation.setDate(evaluationDTO.getDate());
		evaluation.setObservation(evaluationDTO.getObservation());
		evaluation.setScore(evaluationDTO.getScore());
		evaluation.setTitle(evaluationDTO.getTitle());
				
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
