package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInput {
	@Valid
	@NotBlank
	private String email;
	
	@Valid
	@NotBlank
	private String senha;
}
