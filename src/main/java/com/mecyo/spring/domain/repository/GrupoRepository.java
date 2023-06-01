package com.mecyo.spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecyo.spring.domain.model.Cliente;
import com.mecyo.spring.domain.model.Grupo;

public interface GrupoRepository  extends JpaRepository<Grupo, Long> {

	List<Grupo> findByUsuariosIn(List<Cliente> usuario);
}
