package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OcorrenciaInput {
	@Valid
	@NotBlank
	private String descricao;
}
