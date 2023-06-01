package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.PlayerDTO;
import com.mecyo.spring.api.input.PlayerInput;
import com.mecyo.spring.domain.model.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PlayerMapper {

	private ModelMapper modelMapper;

	public PlayerDTO toDTO(Player player) {
		return modelMapper.map(player, PlayerDTO.class);
	}

	public List<PlayerDTO> toCollectionDTO(List<Player> players) {
		return players.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Player toEntity(PlayerInput playerInput) {
		return modelMapper.map(playerInput, Player.class);
	}

	public List<Player> toCollectionEntity(List<PlayerInput> players) {
		return players.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
