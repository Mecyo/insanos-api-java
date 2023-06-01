package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.RankingDTO;
import com.mecyo.spring.domain.model.Ranking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RankingMapper {

	private ModelMapper modelMapper;

	public RankingDTO toDTO(Ranking ranking) {
		return modelMapper.map(ranking, RankingDTO.class);
	}

	public List<RankingDTO> toCollectionDTO(List<Ranking> rankings) {
		return rankings.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
