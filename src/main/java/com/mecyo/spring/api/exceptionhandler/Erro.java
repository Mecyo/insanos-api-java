package com.mecyo.spring.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Erro {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> camposComErro;
	
	
	@Data
	@AllArgsConstructor
	public static class Campo {
		private String nome;
		private String mensagem;
	}
}
