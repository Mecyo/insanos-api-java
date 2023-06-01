package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.EntregaDTO;
import com.mecyo.spring.api.input.EntregaInput;
import com.mecyo.spring.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;

	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}

	public List<Entrega> toCollectionEntity(List<EntregaInput> entregas) {
		return entregas.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
