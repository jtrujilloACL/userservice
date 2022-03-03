package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.userservice.repository.entity.ProgrammingSkill;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
public interface ProgrammingSkillRepository extends JpaRepository<ProgrammingSkill, Long>{
	ProgrammingSkill findByName(String name);
}
