package com.mecyo.spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mecyo.spring.domain.model.Clan;

@Repository
public interface ClanRepository extends JpaRepository<Clan, Long> {

	public List<Clan> findByNome(String nome);

	public List<Clan> findByNomeContaining(String partName);
	
}
