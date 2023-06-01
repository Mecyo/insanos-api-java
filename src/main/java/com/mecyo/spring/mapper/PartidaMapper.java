package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.PartidaDTO;
import com.mecyo.spring.domain.model.Partida;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PartidaMapper {

	private ModelMapper modelMapper;

	public PartidaDTO toDTO(Partida partida) {
		return modelMapper.map(partida, PartidaDTO.class);
	}

	public PartidaDTO toSimpleDTO(Partida partida) {
		return new PartidaDTO(partida);
	}

	public List<PartidaDTO> toCollectionSimpleDTO(List<Partida> partidas) {
		return partidas.stream().map(this::toSimpleDTO).collect(Collectors.toList());
	}

	public List<PartidaDTO> toCollectionDTO(List<Partida> partidas) {
		return partidas.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Partida toEntity(PartidaDTO partidaDTO) {
		return modelMapper.map(partidaDTO, Partida.class);
	}

	public List<Partida> toCollectionEntity(List<PartidaDTO> partidas) {
		return partidas.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
