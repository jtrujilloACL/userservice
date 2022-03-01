package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.userservice.domain.SoftSkill;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 24/02/2022
 */
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {
	SoftSkill findByName(String name);
}
