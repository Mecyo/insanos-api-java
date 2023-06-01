package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.ClanDTO;
import com.mecyo.spring.api.input.ClanInput;
import com.mecyo.spring.domain.model.Clan;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClanMapper {

	private ModelMapper modelMapper;

	public ClanDTO toDTO(Clan clan) {
		return modelMapper.map(clan, ClanDTO.class);
	}

	public List<ClanDTO> toCollectionDTO(List<Clan> clans) {
		return clans.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Clan toEntity(ClanInput clanInput) {
		return modelMapper.map(clanInput, Clan.class);
	}

	public List<Clan> toCollectionEntity(List<ClanInput> clans) {
		return clans.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
