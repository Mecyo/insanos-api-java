package com.mecyo.spring.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecyo.spring.api.dto.PartidaDTO;
import com.mecyo.spring.api.dto.TorneioDTO;
import com.mecyo.spring.domain.exception.NegocioException;
import com.mecyo.spring.domain.model.Partida;
import com.mecyo.spring.domain.model.Player;
import com.mecyo.spring.domain.model.Torneio;
import com.mecyo.spring.domain.repository.TorneioRepository;
import com.mecyo.spring.mapper.PartidaMapper;
import com.mecyo.spring.mapper.TorneioMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TorneioService {

	private TorneioRepository repository;
	private TorneioMapper torneioMapper;
	private PartidaMapper partidaMapper;
	
	
	public List<Torneio> listar() {
		return repository.findAll();
	}
	
	public Torneio buscarPorId(Long idTorneio) {
		return repository.findById(idTorneio)
				.orElseThrow(() -> new NegocioException("Torneio com id: '" + idTorneio + "' não localizado!"));
	}
	
	public Optional<Torneio> getById(Long id) {
		return repository.findById(id);
	}
	
	public List<Torneio> findByNomeContaining(String partName) {
		return repository.findByNomeContaining(partName);
	}
	
	@Transactional
	public Torneio create(Torneio torneio) {
		String nome = torneio.getNome();
		
		boolean nomeEmUso = repository.findByNome(nome)
				.stream()
				.anyMatch(c -> !c.equals(torneio));
		
		if(nomeEmUso) {
			throw new NegocioException("Já existe um torneio registrado com o nome '" + nome + "'");
		}
		
		torneio.setDataRegistro(OffsetDateTime.now());
		
		return repository.save(torneio);
	}
	
	@Transactional
	public Torneio update(Long id, Torneio torneio) {
		torneio.setId(id);
		torneio = create(torneio);
		
		return torneio;
	}

	@Transactional
	public ResponseEntity<Void> delete(Long torneioId) {
		if(!repository.existsById(torneioId)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(torneioId);
		
		return ResponseEntity.noContent().build();
	}

	public boolean existsById(Long torneioId) {
		return repository.existsById(torneioId);
	}

    public TorneioDTO iniciarTorneio() {

		Torneio torneio = new Torneio();

		Integer w = 1;
		Integer x = 1;
		Integer j = 16;
        for (int i = 0; i < 8; i++) {
			torneio.getLeft().add(new Partida(Long.parseLong((w++).toString()), new Player(Long.parseLong((x++).toString()), "Player"+x), new Player(Long.parseLong((x++).toString()), "Player"+j)));
			torneio.getRight().add(new Partida(Long.parseLong((w++).toString()), new Player(Long.parseLong((j++).toString()), "Player"+j), new Player(Long.parseLong((j++).toString()), "Player"+j)));
		}
		
        return torneioMapper.toDTO(torneio);
    }

	public TorneioDTO oitavasDeFinal() {

		Torneio torneio = torneioMapper.toEntity(this.iniciarTorneio());

		Integer w = 17;
		for (int i = 0; i < torneio.getLeft().size(); i = i+2) {
			int one = i;
			int two = i + 1;
			Partida previousOne = torneio.getLeft().get(one);
			Partida previousTwo = torneio.getLeft().get(two);
			Partida next = new Partida(Long.parseLong((w++).toString()), previousOne, previousTwo);
			previousOne.setNext(next);
			previousTwo.setNext(next);
			torneio.getOitavasDeFinal().add(next);
		}

		for (int i = 0; i < torneio.getRight().size(); i = i+2) {
			int one = i;
			int two = i + 1;
			Partida previousOne = torneio.getRight().get(one);
			Partida previousTwo = torneio.getRight().get(two);
			Partida next = new Partida(Long.parseLong((w++).toString()), previousOne, previousTwo);
			previousOne.setNext(next);
			previousTwo.setNext(next);
			torneio.getOitavasDeFinal().add(next);
		}
		
        return torneioMapper.toDTO(torneio);
    }

	public TorneioDTO quartasDeFinal() {

		Torneio torneio = torneioMapper.toEntity(this.oitavasDeFinal());

		Integer w = 25;
        for (int i = 0; i < torneio.getOitavasDeFinal().size(); i = i+2) {
			int one = i;
			int two = i + 1;
			Partida previousOne = torneio.getOitavasDeFinal().get(one);
			Partida previousTwo = torneio.getOitavasDeFinal().get(two);
			Partida next = new Partida(Long.parseLong((w++).toString()), previousOne, previousTwo);
			previousOne.setNext(next);
			previousTwo.setNext(next);
			torneio.getQuartasDeFinal().add(next);
		}
		
        return torneioMapper.toDTO(torneio);
    }

	public TorneioDTO semiFinal() {

		Torneio torneio = torneioMapper.toEntity(this.quartasDeFinal());

		Integer w = 29;
        for (int i = 0; i < torneio.getQuartasDeFinal().size(); i = i+2) {
			int one = i;
			int two = i + 1;
			Partida previousOne = torneio.getQuartasDeFinal().get(one);
			Partida previousTwo = torneio.getQuartasDeFinal().get(two);
			Partida next = new Partida(Long.parseLong((w++).toString()), previousOne, previousTwo);
			previousOne.setNext(next);
			previousTwo.setNext(next);
			torneio.getSemiFinal().add(next);
		}
		
        return torneioMapper.toDTO(torneio);
    }

	public PartidaDTO getFinal() {

		Torneio torneio = torneioMapper.toEntity(this.semiFinal());

		Partida previousOne = torneio.getSemiFinal().get(0);
		Partida previousTwo = torneio.getSemiFinal().get(1);
		Partida next = new Partida(31l, previousOne, previousTwo);
		previousOne.setNext(next);
		previousTwo.setNext(next);
		torneio.setPartidaFinal(next);

		this.imprimirArvore(torneio);

        return partidaMapper.toSimpleDTO(torneio.getPartidaFinal());
    }

	private void imprimirArvore(Torneio torneio) {
		Partida partidaFinal = torneio.getPartidaFinal();
		System.out.println("                                                       Final: " + partidaFinal.getId());
		System.out.println("    						  Semifinal 1: " + partidaFinal.getPreviousOne().getId() + " =========== " + "Semifinal 2: " + partidaFinal.getPreviousTwo().getId());

		System.out.print("              ");
		for(int i = 0; i < torneio.getQuartasDeFinal().size(); i++) {
			Partida partida = torneio.getQuartasDeFinal().get(i);
			System.out.print("Quarta de final " + (i+1) + ": " + partida.getId() + " === ");
		}
		System.out.println("");

		System.out.print("       ");
		for(int i = 0; i < torneio.getOitavasDeFinal().size(); i++) {
			Partida partida = torneio.getOitavasDeFinal().get(i);
			System.out.print("Oitavas de final " + (i+1) + ": " + partida.getId() + " == ");
		}
		System.out.println("");

		System.out.print("   ");
		System.out.println("Classificatórias = Lado A");
		for(int i = 0; i < torneio.getLeft().size(); i++) {
			Partida partida = torneio.getLeft().get(i);
			System.out.println("Jogo " + (i+1) + ": " + partida.getId() + " = " + partida.getPlayerOne().getNickname() + " X " + partida.getPlayerTwo().getNickname());
		}
		System.out.println("");

		System.out.print("   ");
		System.out.println("Classificatórias = Lado B");
		for(int i = 0; i < torneio.getRight().size(); i++) {
			Partida partida = torneio.getRight().get(i);
			System.out.println("Jogo " + (i+1) + ": " + partida.getId() + " = " + partida.getPlayerOne().getNickname() + " X " + partida.getPlayerTwo().getNickname());
		}
		System.out.println("");

	}
}
