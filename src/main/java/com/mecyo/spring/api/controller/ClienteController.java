package com.mecyo.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mecyo.spring.api.dto.ClienteDTO;
import com.mecyo.spring.api.input.ClienteInput;
import com.mecyo.spring.api.input.UserInput;
import com.mecyo.spring.domain.model.Cliente;
import com.mecyo.spring.domain.service.CatalogoClienteService;
import com.mecyo.spring.mapper.ClienteMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private CatalogoClienteService service;
	private ClienteMapper clienteMapper;

	@GetMapping
	public List<ClienteDTO> listar() {
		return clienteMapper.toCollectionDTO(service.listar());
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> getById(@PathVariable Long clienteId) {
		return service.getById(clienteId).map(cliente -> ResponseEntity.ok(clienteMapper.toDTO(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO create(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteMapper.toEntity(clienteInput);
		return clienteMapper.toDTO(service.create(cliente));
	}
	
	@PostMapping("/cadastro")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO cadastrar(@Valid @RequestBody ClienteInput clienteInput) {
		return this.create(clienteInput);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long clienteId, @Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteMapper.toEntity(clienteInput);
		
		if (!service.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteMapper.toDTO(service.update(clienteId, cliente)));
	}
	
	@GetMapping("/filterName")
	public List<ClienteDTO> findByNomeContaining(@RequestParam String partName) {
		return clienteMapper.toCollectionDTO(service.findByNomeContaining(partName));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
		if (!service.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/login")
	public ResponseEntity<ClienteDTO> login(@RequestParam String email, @RequestParam String senha) {
		UserInput user = new UserInput(email, senha);
		return service.verificarLogin(user);
	}
	
	@PostMapping("/send-mail-pass")
	public ResponseEntity<Void> enviarEmailsDeSenha(@RequestBody List<Long> ids) {
		return service.enviarEmailsDeSenha(ids);
	}
	
	@GetMapping("/set-to-admin/{clienteId}")
	public ResponseEntity<Void> addRoleAdmin(@PathVariable Long clienteId) {
		return service.addRoleAdmin(clienteId);
	}

	@PostMapping("/change-password")
	public ResponseEntity<Void> changePassword(@RequestBody ClienteInput clienteInput) {
		return service.changePassword(clienteInput.getId(), clienteInput.getPass());
	}
}
