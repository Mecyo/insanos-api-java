package com.mecyo.spring.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mecyo.spring.api.input.PlayerInput;
import com.mecyo.spring.domain.validation.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Player {

	@Id
	@NotNull(groups = ValidationGroups.PlayerId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nickname;
	
	@Valid
	@OneToOne
	private Clan clan;
	
	private Integer nivel;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataRegistro;
	
	private Integer idUser;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataBanimento;
	
	private String motivoBanimento;
	
	private String banidoPor;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@ManyToOne
	private Cliente cliente;
	
	@ManyToMany
	private List<Torneio> torneios = new ArrayList<>();

	public Player(@Valid PlayerInput playerInput) {
		this.nickname = playerInput.getNickname();
		this.motivoBanimento = playerInput.getMotivoBanimento();
		this.banidoPor = playerInput.getBanidoPor();
		this.dataBanimento = this.dataRegistro = OffsetDateTime.now();
	}

	public Player(Long id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}


	public void ban(@Valid PlayerInput playerInput) {
		this.motivoBanimento = playerInput.getMotivoBanimento();
		this.banidoPor = playerInput.getBanidoPor();
		this.dataBanimento = OffsetDateTime.now();
	}
	
	public void unban() {
		this.motivoBanimento = null;
		this.banidoPor = null;
		this.dataBanimento = null;
	}
}
