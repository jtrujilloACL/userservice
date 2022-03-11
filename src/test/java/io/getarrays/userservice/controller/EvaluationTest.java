package io.getarrays.userservice.controller;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import io.getarrays.userservice.dto.EvaluationDTO;
import io.getarrays.userservice.repository.entity.Evaluation;
import io.getarrays.userservice.service.implementation.EvaluationServiceImplementation;
import io.getarrays.userservice.service.implementation.ProfileServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EvaluationTest {

	@MockBean
	EvaluationServiceImplementation evaluationService;

	@MockBean
	ProfileServiceImplementation profileServiceImplementation;

	@InjectMocks 
	EvaluationController evaluationController;
	
	
	/**
	 * Pre-condiciones para ejecutar los TEST.
	 * Mockito de la capa servicio.
	 */
	@BeforeEach
	void setUp() {
		/*SaveEvaluation*/
		Evaluation eval = mockEvaluation();
		EvaluationDTO evaluationDTO = new EvaluationDTO();
		evaluationDTO.setDate(eval.getDate());
		evaluationDTO.setObservation(eval.getObservation());
		evaluationDTO.setTitle(eval.getTitle());
		evaluationDTO.setScore(eval.getScore());
		evaluationDTO.setProfileId( (long) 9 );
		Mockito.when(evaluationService.save( evaluationDTO )).thenReturn( mockEvaluation() ); //Mockito.any()
		
	}

	@Test
	void testSaveEvaluation() {
		EvaluationDTO evaluationDTO = new EvaluationDTO();
		evaluationDTO.setDate("06/03/2022 ");
		evaluationDTO.setObservation("Observation ");
		evaluationDTO.setTitle("Title ");
		evaluationDTO.setScore(5);
		evaluationDTO.setProfileId((long) 9);

		
		ResponseEntity<Evaluation> actualValue = evaluationController.save(evaluationDTO);
			//Evaluation expectedValue = new Evaluation(null, "06/03/2022", "Title", "Observation", 5);
			//Assertions.assertEquals(actualValue, expectedValue);

		Assertions.assertNotNull(actualValue);
	}

	@Test
	void testGetAllEvaluation() {
		List<Evaluation> expectedValue = evaluationController.getEvaluations();
		Assertions.assertEquals(3, expectedValue.size());
	}

	private EvaluationDTO mockAddEvaluationDTO() {
		EvaluationDTO evaluationDTO = new EvaluationDTO();
		evaluationDTO.setDate("06/03/2022");
		evaluationDTO.setObservation("Observation");
		evaluationDTO.setTitle("Title");
		evaluationDTO.setScore(5);
		evaluationDTO.setProfileId((long) 9);
		return evaluationDTO;
	}
	
	private Evaluation mockEvaluation() {
		Evaluation evaluation = new Evaluation();
		evaluation.setDate("06/03/2022");
		evaluation.setObservation("Observation");
		evaluation.setTitle("Title");
		evaluation.setScore(5);
		return evaluation;
	}
}
