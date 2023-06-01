package com.mecyo.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mecyo.spring.api.dto.ClanDTO;
import com.mecyo.spring.api.input.ClanInput;
import com.mecyo.spring.domain.model.Clan;
import com.mecyo.spring.domain.service.ClanService;
import com.mecyo.spring.mapper.ClanMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clans")
public class ClanController {
	
	private ClanService service;
	private ClanMapper clanMapper;

	@GetMapping
	public List<ClanDTO> listar() {
		return clanMapper.toCollectionDTO(service.listar());
	}
	
	@GetMapping("/{clanId}")
	public ResponseEntity<ClanDTO> getById(@PathVariable Long clanId) {
		return service.getById(clanId).map(clan -> ResponseEntity.ok(clanMapper.toDTO(clan)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClanDTO create(@Valid @RequestBody ClanInput clanInput) {
		Clan clan = clanMapper.toEntity(clanInput);
		return clanMapper.toDTO(service.create(clan));
	}
	
	@PutMapping("/{clanId}")
	public ResponseEntity<ClanDTO> update(@PathVariable Long clanId, @Valid @RequestBody ClanInput clanInput) {
		Clan clan = clanMapper.toEntity(clanInput);
		
		if (!service.existsById(clanId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clanMapper.toDTO(service.update(clanId, clan)));
	}
	
	@GetMapping("/filterName")
	public List<ClanDTO> findByNomeContaining(@RequestParam String partName) {
		return clanMapper.toCollectionDTO(service.findByNomeContaining(partName));
	}
	
	@DeleteMapping("/{clanId}")
	public ResponseEntity<Void> delete(@PathVariable Long clanId) {
		if (!service.existsById(clanId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(clanId);
		
		return ResponseEntity.noContent().build();
	}
}
