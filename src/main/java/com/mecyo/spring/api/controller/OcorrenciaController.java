package com.mecyo.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mecyo.spring.api.dto.OcorrenciaDTO;
import com.mecyo.spring.api.input.OcorrenciaInput;
import com.mecyo.spring.domain.service.RegistroOcorrenciaService;
import com.mecyo.spring.mapper.OcorrenciaMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private RegistroOcorrenciaService service;
	private OcorrenciaMapper ocorrenciaMapper;

	@GetMapping
	public List<OcorrenciaDTO> listarPorEntrega(@PathVariable Long entregaId) {
		return ocorrenciaMapper.toCollectionDTO(service.listarPorEntrega(entregaId));
	}
	
	@GetMapping("/{ocorrenciaId}")
	public ResponseEntity<OcorrenciaDTO> getById(@PathVariable Long ocorrenciaId) {
		return service.getById(ocorrenciaId).map(ocorrencia -> ResponseEntity.ok(ocorrenciaMapper.toDTO(ocorrencia)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		return ocorrenciaMapper.toDTO(service.registrar(entregaId, ocorrenciaInput.getDescricao()));
	}
	
	@DeleteMapping("/{ocorrenciaId}")
	public ResponseEntity<Void> delete(@PathVariable Long ocorrenciaId) {
		if (!service.existsById(ocorrenciaId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(ocorrenciaId);
		
		return ResponseEntity.noContent().build();
	}
}
