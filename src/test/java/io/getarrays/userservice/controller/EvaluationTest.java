package io.getarrays.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import io.getarrays.userservice.dto.EvaluationDTO;
import io.getarrays.userservice.repository.EvaluationRepository;
import io.getarrays.userservice.repository.ProfileRepository;
import io.getarrays.userservice.repository.entity.Evaluation;
import io.getarrays.userservice.service.implementation.EvaluationServiceImplementation;
import io.getarrays.userservice.service.implementation.ProfileServiceImplementation;

class EvaluationTest {

	ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
	
	@Autowired
	ProfileServiceImplementation profileService = new ProfileServiceImplementation(profileRepository);
	
	@Autowired
	EvaluationDTO evaluationDTO = new EvaluationDTO();
	
	EvaluationRepository evaluationRepository = Mockito.mock(EvaluationRepository.class);
	
	@Autowired
	EvaluationServiceImplementation evaluationService = new EvaluationServiceImplementation(evaluationRepository, profileService);
		
	@InjectMocks
	EvaluationController evaluationController = new EvaluationController();

	
	@BeforeEach
	void setUp(){
		/*Save a Evaluation*/
		Evaluation mockEvaluation = new Evaluation();
		mockEvaluation.setDate("06/03/2022");
		mockEvaluation.setObservation("Observation");
		mockEvaluation.setTitle("Title");
		mockEvaluation.setScore(5);
		Mockito.when( evaluationRepository.save(mockEvaluation)).thenReturn(mockEvaluation);
		
		/*Get all evaluation, return 3 evaluations*/
		Evaluation mockEvaluation1 = newAddEvaluation("1");
		Mockito.when( evaluationRepository.save(mockEvaluation1)).thenReturn(mockEvaluation1);
		Evaluation mockEvaluation2 = newAddEvaluation("2");
		Mockito.when( evaluationRepository.save(mockEvaluation2)).thenReturn(mockEvaluation2);
		Evaluation mockEvaluation3 = newAddEvaluation("3");
		Mockito.when( evaluationRepository.save(mockEvaluation3)).thenReturn(mockEvaluation3);
		
		List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
		listEvaluations.add(mockEvaluation1);
		listEvaluations.add(mockEvaluation2);
		listEvaluations.add(mockEvaluation3);
		Mockito.when( evaluationService.findAll() ).thenReturn( listEvaluations );

	}
	
	
	
	@Test
	void testSaveEvaluation(){
		//ResponseEntity<Evaluation> actualValue = evaluationController.save(evaluationDTO);

		EvaluationDTO evaluationDTO = new EvaluationDTO();
		evaluationDTO.setDate("06/03/2022");
		evaluationDTO.setObservation("Observation");
		evaluationDTO.setTitle("Title");
		evaluationDTO.setScore(5);
		evaluationDTO.setProfileId((long)9);
		
		Evaluation actualValue = evaluationService.save(evaluationDTO);
		Evaluation expectedValue = new Evaluation(null, "06/03/2022","Title","Observation",5);
		Assertions.assertEquals(actualValue,expectedValue);
	}
	
	@Test
	void testGetAllEvaluation(){
		List<Evaluation> expectedValue = evaluationService.findAll();

		Assertions.assertEquals(3, expectedValue.size());
		//verify(dao, times(1)).getEmployeeList();
	}
	
	
	private Evaluation newAddEvaluation(String num){
		EvaluationDTO evaluationDTO = new EvaluationDTO();
		evaluationDTO.setDate("06/03/2022");
		evaluationDTO.setObservation("Observation"+num);
		evaluationDTO.setTitle("Title"+num);
		evaluationDTO.setScore(5+Integer.parseInt(num));
		evaluationDTO.setProfileId((long)9);
		return evaluationService.save(evaluationDTO);
	}
}
