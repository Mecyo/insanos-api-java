package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.TorneioDTO;
import com.mecyo.spring.api.input.TorneioInput;
import com.mecyo.spring.domain.model.Torneio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TorneioMapper {

	private ModelMapper modelMapper;

	public TorneioDTO toDTO(Torneio torneio) {
		return modelMapper.map(torneio, TorneioDTO.class);
	}

	public List<TorneioDTO> toCollectionDTO(List<Torneio> torneios) {
		return torneios.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Torneio toEntity(TorneioInput torneioInput) {
		return modelMapper.map(torneioInput, Torneio.class);
	}

	public Torneio toEntity(TorneioDTO torneioInput) {
		return modelMapper.map(torneioInput, Torneio.class);
	}

	public List<Torneio> toCollectionEntity(List<TorneioInput> torneios) {
		return torneios.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
