package com.mecyo.spring.domain.service;

import org.springframework.stereotype.Service;

import com.mecyo.spring.domain.exception.EntityNotFoundException;
import com.mecyo.spring.domain.model.Entrega;
import com.mecyo.spring.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository repository;

	public Entrega buscar(Long entregaId) {
		return repository.findById(entregaId).orElseThrow(() -> new EntityNotFoundException("Entrega não localizada"));
	}

}
