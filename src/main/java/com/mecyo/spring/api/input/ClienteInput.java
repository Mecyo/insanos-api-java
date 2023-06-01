package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClienteInput {
	@Valid
	private Long id;
	
	@Valid
	@NotBlank
	private String nome;

	@Valid
	@NotBlank
	private String email;

	@Valid
	@NotBlank
	private String telefone;

	private String pass;
}
