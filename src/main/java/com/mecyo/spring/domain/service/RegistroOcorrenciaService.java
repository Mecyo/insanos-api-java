package com.mecyo.spring.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecyo.spring.domain.model.Entrega;
import com.mecyo.spring.domain.model.Ocorrencia;
import com.mecyo.spring.domain.repository.OcorrenciaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private OcorrenciaRepository repository;
	private BuscaEntregaService buscaEntregaService;

	public Optional<Ocorrencia> getById(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);

		return entrega.adicionarOcorrencia(descricao);
	}

	@Transactional
	public ResponseEntity<Ocorrencia> update(Long id, Ocorrencia ocorrencia) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		ocorrencia.setId(id);

		return ResponseEntity.ok(repository.save(ocorrencia));
	}

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long ocorrenciaId) {
		return repository.existsById(ocorrenciaId);
	}

	public List<Ocorrencia> listarPorEntrega(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.getOcorrencias();
	}
}
