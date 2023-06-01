package com.mecyo.spring.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GrupoUsuario {

	SUPER_GROUP(1L, "Grupo de super usuários"),
	ADMIN_GROUP(2L, "Grupo de usuários administradores"),
	USER_GROUP(3L, "Grupo de usuários comuns");
	
	private Long id;
	private String  descricao;
}
