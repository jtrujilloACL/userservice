package io.getarrays.userservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.getarrays.userservice.dto.EvaluationDTO;
import io.getarrays.userservice.dto.EvaluationToProfileDTO;
import io.getarrays.userservice.repository.entity.Evaluation;
import io.getarrays.userservice.repository.entity.Profile;
import io.getarrays.userservice.service.implementation.EvaluationServiceImplementation;
import io.getarrays.userservice.service.implementation.ProfileServiceImplementation;
import io.getarrays.userservice.utils.UtilToken;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.2
 * @since 24/02/2022 04/03/2022
 */
@RestController
@RequestMapping("/api/evaluation")
@Slf4j
public class EvaluationController {
	
	@Autowired
	private EvaluationServiceImplementation evaluationService;
	
	@Autowired
	private ProfileServiceImplementation profileService;
	
	@PostMapping("/save")
	public ResponseEntity<Evaluation> save(HttpServletRequest request, @RequestBody EvaluationDTO evaluationDTO){
		log.info("Save a new evaluation: {} to the database",evaluationDTO.getTitle());
		/*String usernameProfile = UtilToken.getUsernameToToken(request);
		evaluationDTO.setProfileId(usernameProfile);*/
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/evaluation/save").toString() );
		return ResponseEntity.created(uri).body( evaluationService.save(evaluationDTO));
	}
	
	@GetMapping("/all")
	public List<Evaluation> getEvaluations(){
		return StreamSupport
				.stream(evaluationService.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEvaluations(@RequestBody EvaluationDTO evalDTO, @PathVariable(value = "id") Long evalId) {
		Optional<Evaluation> evaluation = evaluationService.findById(evalId);

		if (!evaluation.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.update(evalDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(value="id") Long id ){
		if (!evaluationService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		evaluationService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/addtouser")
	public ResponseEntity<?> addEvaluationToProfile(@RequestBody EvaluationToProfileDTO form){
		log.info("Add Evaluation to profile: {} to User {}",form.getId(),form.getIdentityDocument() );
		
		Optional<Evaluation> eval = evaluationService.findById(form.getId());
		Profile profile= profileService.findByIdentityDocument(form.getIdentityDocument());
		
		if( !eval.isPresent() || profile == null ){
			return ResponseEntity.notFound().build();
		}
		
		evaluationService.addEvaluationToProfile( form.getId(), form.getIdentityDocument());
		return ResponseEntity.ok().build();
	}
}
