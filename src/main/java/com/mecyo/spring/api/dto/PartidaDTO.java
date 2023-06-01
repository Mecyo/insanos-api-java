package com.mecyo.spring.api.dto;

import com.mecyo.spring.domain.model.Partida;

import lombok.Data;

@Data
public class PartidaDTO {

	private Long id;
    
    private String playerOne;
    private String playerTwo;
    private String winner;
    
    private Long next;
    private Long previousOne;
    private Long previousTwo;

    public PartidaDTO() {}
    
    public PartidaDTO(Partida partida) {
        this.id = partida.getId();
        this.playerOne = partida.getPlayerOne() != null ? partida.getPlayerOne().getNickname() : null;
        this.playerTwo = partida.getPlayerTwo() != null ? partida.getPlayerTwo().getNickname() : null;
        this.winner = partida.getWinner() != null ? partida.getWinner().getNickname() : null;
        this.next = partida.getNext() != null ? partida.getNext().getId() : null;
        this.previousOne = partida.getPreviousOne() != null ? partida.getPreviousOne().getId() : null;
        this.previousTwo = partida.getPreviousTwo() != null ? partida.getPreviousTwo().getId() : null;
    }
}
