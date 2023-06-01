package com.mecyo.spring.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class Destinatario {

	@NotBlank
	@Size(max = 60)
	@Column(name="destinatario_nome")
	private String nome;

	@NotBlank
	@Size(max=255)
	@Column(name="destinatario_logradouro")
	private String logradouro;

	@NotBlank
	@Size(max=30)
	@Column(name="destinatario_numero")
	private String numero;

	@Size(max=60)
	@Column(name="destinatario_complemento")
	private String complemento;

	@NotBlank
	@Size(max=30)
	@Column(name="destinatario_bairro")
	private String bairro;
	
}
