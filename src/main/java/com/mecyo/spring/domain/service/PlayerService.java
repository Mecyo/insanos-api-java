package com.mecyo.spring.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecyo.spring.api.dto.PlayerDTO;
import com.mecyo.spring.api.input.PlayerInput;
import com.mecyo.spring.domain.exception.NegocioException;
import com.mecyo.spring.domain.model.Player;
import com.mecyo.spring.domain.repository.PlayerRepository;
import com.mecyo.spring.mapper.PlayerMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlayerService {

	private PlayerRepository repository;

	private PlayerMapper playerMapper; 
	
	public List<Player> listar() {
		return repository.findAll();
	}
	
	public Page<PlayerDTO> listarBanidos(Pageable page) {
		Page<Player> result = repository.findAllBanned(page);
		return result.map(object -> playerMapper.toDTO(object));
	}
	
	public Page<PlayerDTO> listarBanidosPorNickname(String nickname, Pageable page) {
		Page<Player> result = repository.findBannedByNicknameContaining(nickname, page);
		return result.map(object -> playerMapper.toDTO(object));
	}
	
	public Player buscarPorId(Long idPlayer) {
		return repository.findById(idPlayer)
				.orElseThrow(() -> new NegocioException("Player com id: '" + idPlayer + "' não localizado!"));
	}
	
	public Optional<Player> getById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Player create(Player player) {
		String nickname = player.getNickname();
		
		if(repository.findByNickname(nickname) != null) {
			throw new NegocioException("Já existe um player registrado com o nickname '" + nickname + "'");
		}
		
		player.setDataRegistro(OffsetDateTime.now());
		
		return repository.save(player);
	}
	
	@Transactional
	public Player update(Long id, Player player) {
		player.setId(id);
		player = create(player);
		
		return player;
	}
	
	public boolean existsById(Long playerId) {
		return repository.existsById(playerId);
	}

	@Transactional
	public ResponseEntity<Void> delete(Long playerId) {
		if(!repository.existsById(playerId)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(playerId);
		
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Void> ban(@Valid PlayerInput playerInput) {
		Player player = repository.findByNickname(playerInput.getNickname());
		
		if(player != null) {
			player.ban(playerInput);
		} else {
			player = new Player(playerInput);
		}
		
		repository.save(player);
		
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Void> unban(Long playerId) {
		Optional<Player> optPlayer = repository.findById(playerId);
		
		if(!optPlayer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Player player = optPlayer.get();
		player.unban();
		repository.save(player);
		
		return ResponseEntity.noContent().build();
	}
}
