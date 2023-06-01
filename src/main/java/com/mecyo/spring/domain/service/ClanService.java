package com.mecyo.spring.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecyo.spring.domain.exception.NegocioException;
import com.mecyo.spring.domain.model.Clan;
import com.mecyo.spring.domain.repository.ClanRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClanService {

	private ClanRepository repository;
	
	
	public List<Clan> listar() {
		return repository.findAll();
	}
	
	public Clan buscarPorId(Long idClan) {
		return repository.findById(idClan)
				.orElseThrow(() -> new NegocioException("Clan com id: '" + idClan + "' não localizado!"));
	}
	
	public Optional<Clan> getById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Clan create(Clan clan) {
		String nome = clan.getNome();
		
		boolean nomeEmUso = repository.findByNome(nome)
				.stream()
				.anyMatch(c -> !c.equals(clan));
		
		if(nomeEmUso) {
			throw new NegocioException("Já existe um clan cadastrado com o nome '" + nome + "'");
		}
		
		return repository.save(clan);
	}
	
	public List<Clan> findByNomeContaining(String partName) {
		return repository.findByNomeContaining(partName);
	}

	@Transactional
	public Clan update(Long id, Clan clan) {
		clan.setId(id);
		clan = create(clan);
		
		return clan;
	}

	@Transactional
	public void delete(Long clanId) {
		repository.deleteById(clanId);
	}
	
	public boolean existsById(Long clanId) {
		return repository.existsById(clanId);
	}
}
