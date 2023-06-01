package com.mecyo.spring.api.dto;

import lombok.Data;

@Data
public class PartidaDTOId {

	private Long id;
    
    private PlayerDTO playerOne;
    private PlayerDTO playerTwo;
    private PlayerDTO winner;
    
    private Long nextId;
    private Long previousOneId;
    private Long previousTwoId;
}
