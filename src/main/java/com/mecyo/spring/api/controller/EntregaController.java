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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mecyo.spring.api.dto.EntregaDTO;
import com.mecyo.spring.api.input.EntregaInput;
import com.mecyo.spring.domain.model.Entrega;
import com.mecyo.spring.domain.service.FinalizacaoEntregaService;
import com.mecyo.spring.domain.service.SolicitacaoEntregaService;
import com.mecyo.spring.mapper.EntregaMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private SolicitacaoEntregaService service;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private EntregaMapper entregaMapper;

	@GetMapping
	public List<EntregaDTO> listar() {
		return entregaMapper.toCollectionDTO(service.listar());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> getById(@PathVariable Long entregaId) {
		return service.getById(entregaId).map(entrega -> ResponseEntity.ok(entregaMapper.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega entrega = entregaMapper.toEntity(entregaInput);
		return entregaMapper.toDTO(service.solicitar(entrega));
	}
	
	@PutMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> update(@PathVariable Long entregaId, @Valid @RequestBody EntregaInput entregaInput) {
		Entrega entrega = entregaMapper.toEntity(entregaInput);
		
		if (!service.existsById(entregaId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(entregaMapper.toDTO(service.update(entregaId, entrega)));
	}
	
	@DeleteMapping("/{entregaId}")
	public ResponseEntity<Void> delete(@PathVariable Long entregaId) {
		if (!service.existsById(entregaId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(entregaId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
}
