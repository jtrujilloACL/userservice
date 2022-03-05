package io.getarrays.userservice.dto;

import lombok.Data;

@Data
public class EvaluationDTO {
	private String date;
	private String title;
	private String observation;
	private Integer score;
	private Long profileId;
}
