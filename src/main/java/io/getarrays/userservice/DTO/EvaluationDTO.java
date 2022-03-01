package io.getarrays.userservice.DTO;

import lombok.Data;

@Data
public class EvaluationDTO {
	private String date;
	private String title;
	private String observation;
	private Integer score;
}
