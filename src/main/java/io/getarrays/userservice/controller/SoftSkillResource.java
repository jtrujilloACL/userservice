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

import io.getarrays.userservice.dto.SoftSkillDTO;
import io.getarrays.userservice.repository.entity.SoftSkill;
import io.getarrays.userservice.service.implementation.SoftSkillServiceImplementation;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 01/03/2022
 */
@RestController
@RequestMapping("/api/soft-skill")
public class SoftSkillResource {

	@Autowired
	private SoftSkillServiceImplementation softSkillService;
	
	@PostMapping
	public ResponseEntity<?> saveProgrammingSkill(@RequestBody SoftSkill softSkill){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/evaluation/save").toString() );
		return ResponseEntity.created(uri).body( softSkillService.save(softSkill));
	}
	
	@GetMapping("/all")
	public List<SoftSkill> getSkill(){
		return StreamSupport
				.stream(softSkillService.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSkill(@RequestBody SoftSkillDTO skillDetail, @PathVariable(value = "id") Long skillId) {
		Optional<SoftSkill> skill = softSkillService.findById(skillId);

		if (!skill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		skill.get().setName(skillDetail.getName());
		skill.get().setDescription(skillDetail.getDescription());
		return ResponseEntity.status(HttpStatus.CREATED).body(softSkillService.save(skill.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSkill(@PathVariable(value="id") Long id ){
		if (!softSkillService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		softSkillService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}