package com.mecyo.spring.api.dto;

import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String token;
}
