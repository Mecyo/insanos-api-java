package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TorneioInput {
	@Valid
	private Long id;
	
	@Valid
	@NotBlank
	private String nome;
}
