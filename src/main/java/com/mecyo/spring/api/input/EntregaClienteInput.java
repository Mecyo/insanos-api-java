package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntregaClienteInput {
	@Valid
	@NotNull
	private Long id;
}
