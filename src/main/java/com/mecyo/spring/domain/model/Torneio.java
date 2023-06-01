package com.mecyo.spring.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mecyo.spring.domain.validation.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Torneio {

	@Id
	@NotNull(groups = ValidationGroups.TorneioId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataRegistro;

	@Transient
	private List<Partida> left = new ArrayList<>();
	@Transient
	private List<Partida> right = new ArrayList<>();
	@Transient
	private List<Partida> oitavasDeFinal = new ArrayList<>();
	@Transient
	private List<Partida> quartasDeFinal = new ArrayList<>();
	@Transient
	private List<Partida> semiFinal = new ArrayList<>();
	@Transient
	private Partida partidaFinal;
	
	@ManyToMany
	private List<Player> players = new ArrayList<>();
}
