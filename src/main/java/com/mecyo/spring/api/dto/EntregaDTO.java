package com.mecyo.spring.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.mecyo.spring.domain.enums.StatusEntrega;

import lombok.Data;

@Data
public class EntregaDTO {
	private Long id;
	private BigDecimal taxa;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	private String nomeCliente;
	private DestinatarioDTO destinatario;
	private StatusEntrega status;
	
	private List<OcorrenciaDTO> ocorrencias;
}
