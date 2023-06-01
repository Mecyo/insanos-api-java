package com.mecyo.spring.api.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntregaInput {

	@Valid
	@NotNull
	private EntregaClienteInput cliente;
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario;
	
	@Valid
	@NotNull
	private BigDecimal taxa;
}
