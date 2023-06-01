package com.mecyo.spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mecyo.spring.domain.model.Torneio;

@Repository
public interface TorneioRepository extends JpaRepository<Torneio, Long> {

	public List<Torneio> findByNome(String nome);

	public List<Torneio> findByNomeContaining(String partName);
	
}
