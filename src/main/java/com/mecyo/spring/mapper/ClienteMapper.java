package com.mecyo.spring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mecyo.spring.api.dto.ClienteDTO;
import com.mecyo.spring.api.input.ClienteInput;
import com.mecyo.spring.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteMapper {

	private ModelMapper modelMapper;

	public ClienteDTO toDTO(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	public List<ClienteDTO> toCollectionDTO(List<Cliente> clientes) {
		return clientes.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

	public List<Cliente> toCollectionEntity(List<ClienteInput> clientes) {
		return clientes.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
