package com.mecyo.spring.api.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ClanDTO {

	private Long id;
	private String nome;
	private OffsetDateTime dataRegistro;
}
