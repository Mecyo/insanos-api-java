package com.mecyo.spring.api.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PlayerDTO {

	private Long id;
	private String nickname;
	private Integer nivel;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private OffsetDateTime dataRegistro;
	private ClanDTO clan;
	private ClienteDTO cliente;
	private String motivoBanimento;
	private String banidoPor;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private OffsetDateTime dataBanimento;
}
