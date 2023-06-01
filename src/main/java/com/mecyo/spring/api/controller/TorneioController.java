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

import com.mecyo.spring.api.dto.PartidaDTO;
import com.mecyo.spring.api.dto.TorneioDTO;
import com.mecyo.spring.api.input.TorneioInput;
import com.mecyo.spring.domain.model.Torneio;
import com.mecyo.spring.domain.service.TorneioService;
import com.mecyo.spring.mapper.TorneioMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/torneios")
public class TorneioController {
	
	private TorneioService service;
	private TorneioMapper torneioMapper;

	@GetMapping
	public List<TorneioDTO> listar() {
		return torneioMapper.toCollectionDTO(service.listar());
	}
	
	@GetMapping("/{torneioId}")
	public ResponseEntity<TorneioDTO> getById(@PathVariable Long torneioId) {
		return service.getById(torneioId).map(torneio -> ResponseEntity.ok(torneioMapper.toDTO(torneio)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TorneioDTO create(@Valid @RequestBody TorneioInput torneioInput) {
		Torneio torneio = torneioMapper.toEntity(torneioInput);
		return torneioMapper.toDTO(service.create(torneio));
	}
	
	@PutMapping("/{torneioId}")
	public ResponseEntity<TorneioDTO> update(@PathVariable Long torneioId, @Valid @RequestBody TorneioInput torneioInput) {
		Torneio torneio = torneioMapper.toEntity(torneioInput);
		
		if (!service.existsById(torneioId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(torneioMapper.toDTO(service.update(torneioId, torneio)));
	}
	
	@GetMapping("/filterName")
	public List<TorneioDTO> findByNomeContaining(@RequestParam String partName) {
		return torneioMapper.toCollectionDTO(service.findByNomeContaining(partName));
	}
	
	@DeleteMapping("/{torneioId}")
	public ResponseEntity<Void> delete(@PathVariable Long torneioId) {
		if (!service.existsById(torneioId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(torneioId);
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/iniciarTorneio")
	public TorneioDTO iniciarTorneio() {
		return service.iniciarTorneio();
	}

	@GetMapping("/getOitavasDeFinal")
	public List<PartidaDTO> getOitavasDeFinal() {
		return service.oitavasDeFinal().getOitavasDeFinal();
	}

	@GetMapping("/getQuartasDeFinal")
	public List<PartidaDTO> getQuartasDeFinal() {
		return service.quartasDeFinal().getQuartasDeFinal();
	}

	@GetMapping("/getSemiFinal")
	public List<PartidaDTO> getSemiFinal() {
		return service.semiFinal().getSemiFinal();
	}

	@GetMapping("/getPartidaFinal")
	public PartidaDTO getFinal() {
		return service.getFinal();
	}
}
