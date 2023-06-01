package com.mecyo.spring.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TorneioDTO {

	private Long id;
	private String nome;
	private OffsetDateTime dataRegistro;

	private List<PartidaDTO> left;
	private List<PartidaDTO> right;

	private List<PartidaDTO> oitavasDeFinal;
	private List<PartidaDTO> quartasDeFinal;
	private List<PartidaDTO> semiFinal;
	private PartidaDTO partidaFinal;
}
