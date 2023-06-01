package com.mecyo.spring.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEntrega {

	PENDENTE(1, "Pendente"),
	FINALIZADA(2, "Finalizada"),
	CANCELADA(3, "Cancelada");
	
	private Integer id;
	private String  descricao;
}
