package com.mecyo.spring.domain.service;

import org.springframework.stereotype.Service;

import com.mecyo.spring.domain.model.Entrega;
import com.mecyo.spring.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository repository;
	private BuscaEntregaService buscaEntregaService;
	
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();

		repository.save(entrega);
	}

}
