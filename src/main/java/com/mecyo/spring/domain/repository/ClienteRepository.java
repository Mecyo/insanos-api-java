package com.mecyo.spring.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mecyo.spring.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByNome(String nome);
	
	public Optional<Cliente> findByEmail(String email);
	
	public List<Cliente> findByTelefone(String telefone);
	
	public Optional<Cliente> findById(Long id);
	
	public List<Cliente> findByNomeContaining(String nome);
	
	public Optional<Cliente> findByEmailAndSenha(String email, String senha);
}
