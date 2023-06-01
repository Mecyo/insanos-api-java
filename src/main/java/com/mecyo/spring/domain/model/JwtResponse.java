package com.mecyo.spring.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mecyo.spring.security.UsuarioSistema;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private Long id;
	private String token;
	private String nome;
	private String email;
	private List<String> permissoes = new ArrayList<>();

	public JwtResponse(UsuarioSistema userDetails, String token) {
		this.id = userDetails.getId();
		this.nome = userDetails.getNome();
		this.email = userDetails.getUsername();
		this.token = token;
		userDetails.getAuthorities().forEach(aut -> this.permissoes.add(aut.getAuthority()));
	}

}