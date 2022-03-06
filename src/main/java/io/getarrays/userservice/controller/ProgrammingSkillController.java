package io.getarrays.userservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import io.getarrays.userservice.dto.ProgrammingSkillDTO;
import io.getarrays.userservice.repository.entity.ProgrammingSkill;
import io.getarrays.userservice.service.implementation.ProgrammingSkillServiceImplementation;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
@RestController
@RequestMapping("/api/programming-skill")
public class ProgrammingSkillController {

	@Autowired
	private ProgrammingSkillServiceImplementation programmingSkillService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveProgrammingSkill(@RequestBody ProgrammingSkillDTO programmingSkillDTO){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/evaluation/save").toString() );
		return ResponseEntity.created(uri).body( programmingSkillService.save(programmingSkillDTO));
	}
	
	@GetMapping("/all")
	public List<ProgrammingSkill> getSkill(){
		return StreamSupport
				.stream(programmingSkillService.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSkill(@RequestBody ProgrammingSkillDTO skillDetailDTO, @PathVariable(value = "id") Long skillId) {
		Optional<ProgrammingSkill> skill = programmingSkillService.findById(skillId);
		if (!skill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(programmingSkillService.update(skillDetailDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSkill(@PathVariable(value="id") Long id ){
		if (!programmingSkillService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		programmingSkillService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
