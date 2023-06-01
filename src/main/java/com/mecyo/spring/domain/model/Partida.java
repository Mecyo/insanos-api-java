package com.mecyo.spring.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.mecyo.spring.domain.validation.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Entity
@NoArgsConstructor
public class Partida {

    @Id
    @NotNull(groups = ValidationGroups.PlayerId.class)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Player playerOne;
    private Player playerTwo;
    private Player winner;
    
    @JsonIdentityReference(alwaysAsId = true)
    private Partida next;
    @JsonIdentityReference(alwaysAsId = true)
    private Partida previousOne;
    @JsonIdentityReference(alwaysAsId = true)
    private Partida previousTwo;

    public Partida(Long id) {
        this.id = id;
    }

    public Partida(Long id, Player playerOne, Player playerTwo) {
        this.id = id;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Partida(Long id, Partida previousOne, Partida previousTwo) {
        this.id = id;
        this.previousOne = previousOne;
        this.previousTwo = previousTwo;
    }
}
