package com.mecyo.spring.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecyo.spring.domain.model.Grupo;
import com.mecyo.spring.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	List<Permissao> findByGruposIn(List<Grupo> grupo);

}