package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.OcorrenciaDTO;
import com.mecyo.spring.api.input.OcorrenciaInput;
import com.mecyo.spring.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private ModelMapper modelMapper;

	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Ocorrencia toEntity(OcorrenciaInput ocorrenciaInput) {
		return modelMapper.map(ocorrenciaInput, Ocorrencia.class);
	}

	public List<Ocorrencia> toCollectionEntity(List<OcorrenciaInput> ocorrencias) {
		return ocorrencias.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
