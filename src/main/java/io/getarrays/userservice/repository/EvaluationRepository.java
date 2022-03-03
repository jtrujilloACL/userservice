package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.getarrays.userservice.repository.entity.Evaluation;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 23/02/2022
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	Evaluation findByTitle(String title);
}
