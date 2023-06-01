package com.mecyo.spring.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PlayerInput {
	@Valid
	private Long id;
	
	@Valid
	@NotBlank
	private String nickname;
	
	@Valid
	private Integer nivel;

	@Valid
	private ClanInput clan;

	@Valid
	private ClienteInput cliente;
	
	@Valid
	private String motivoBanimento;
	
	@Valid
	private String banidoPor;
}
