package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DestinatarioInput {

	@Valid
	@NotBlank
	private String nome;
	
	@Valid
	@NotBlank
	private String logradouro;
	
	@Valid
	@NotBlank
	private String numero;
	
	@Valid
	@NotBlank
	private String bairro;
}
