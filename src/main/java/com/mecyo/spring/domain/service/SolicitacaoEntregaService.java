package com.mecyo.spring.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecyo.spring.domain.enums.StatusEntrega;
import com.mecyo.spring.domain.model.Cliente;
import com.mecyo.spring.domain.model.Entrega;
import com.mecyo.spring.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository repository;
	private CatalogoClienteService clienteService;

	public List<Entrega> listar() {
		return repository.findAll();
	}

	public Optional<Entrega> getById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Long idCliente = entrega.getCliente().getId();
		Cliente cliente = clienteService.buscarPorId(idCliente);

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return repository.save(entrega);
	}

	@Transactional
	public Entrega update(Long id, Entrega entrega) {
		entrega.setId(id);

		return solicitar(entrega);
	}

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long entregaId) {
		return repository.existsById(entregaId);
	}
}
