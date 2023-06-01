package com.mecyo.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mecyo.spring.api.dto.PlayerDTO;
import com.mecyo.spring.api.input.PlayerInput;
import com.mecyo.spring.domain.model.Player;
import com.mecyo.spring.domain.service.PlayerService;
import com.mecyo.spring.mapper.PlayerMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

	private PlayerService service;
	private PlayerMapper playerMapper;

	@GetMapping
	public List<PlayerDTO> listar() {
		return playerMapper.toCollectionDTO(service.listar());
	}

	@GetMapping("/banned")
	public Page<PlayerDTO> listarBanidos(@RequestParam String nickname, Pageable page) {
		if (Strings.isEmpty(nickname)) {
			return service.listarBanidos(page);
		}

		return service.listarBanidosPorNickname(nickname, page);
	}

	@GetMapping("/{playerId}")
	public ResponseEntity<PlayerDTO> getById(@PathVariable Long playerId) {
		return service.getById(playerId).map(player -> ResponseEntity.ok(playerMapper.toDTO(player)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PlayerDTO create(@Valid @RequestBody PlayerInput playerInput) {
		Player player = playerMapper.toEntity(playerInput);
		return playerMapper.toDTO(service.create(player));
	}

	@PostMapping("/ban")
	public ResponseEntity<Void> ban(@Valid @RequestBody PlayerInput playerInput) {
		return service.ban(playerInput);
	}
	
	@GetMapping("/unban/{playerId}")
	public ResponseEntity<Void> unban(@PathVariable Long playerId) {
		return service.unban(playerId);
	}

	@PutMapping("/{playerId}")
	public ResponseEntity<PlayerDTO> update(@PathVariable Long playerId, @Valid @RequestBody Player player) {
		if (!service.existsById(playerId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(playerMapper.toDTO(service.update(playerId, player)));
	}

	@DeleteMapping("/{playerId}")
	public ResponseEntity<Void> delete(@PathVariable Long playerId) {
		return service.delete(playerId);
	}
}
