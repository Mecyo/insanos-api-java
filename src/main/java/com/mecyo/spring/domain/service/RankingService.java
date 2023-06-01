package com.mecyo.spring.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mecyo.spring.api.dto.RankingDTO;
import com.mecyo.spring.domain.exception.NegocioException;
import com.mecyo.spring.domain.model.Ranking;
import com.mecyo.spring.domain.repository.RankingRepository;
import com.mecyo.spring.mapper.RankingMapper;
import com.mecyo.spring.utils.RankingCalculator;
import com.mecyo.spring.utils.Utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RankingService {

	private RankingRepository repository;

	private RankingCalculator rankingCalculator; 
	
	private RankingMapper mapper;
	
	public List<RankingDTO> listar() {
		List<RankingDTO> listRankings = mapper.toCollectionDTO(repository.findAll());
		
		this.calcularPosicao(listRankings);
		return listRankings;
	}
	
	private void calcularPosicao(List<RankingDTO> listRankings) {
		List<RankingDTO> sortedList = listRankings.stream().sorted(Comparator.comparingInt(RankingDTO::getTotalPoints).reversed()).collect(Collectors.toList());
		for(int i = 0; i < sortedList.size(); i++) {
			sortedList.get(i).setPosicao(i+1);
		}
	}

	public Ranking buscarPorId(Long idRanking) {
		return repository.findById(idRanking)
				.orElseThrow(() -> new NegocioException("Ranking com id: '" + idRanking + "' não localizado!"));
	}
	
	public Optional<Ranking> getById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Ranking create(Ranking ranking) {
		ranking.setAdmin(Utils.getUserName());
		
		return repository.save(ranking);
	}
	
	public boolean existsById(Long rankingId) {
		return repository.existsById(rankingId);
	}

	@Transactional
	public ResponseEntity<Void> delete(Long rankingId) {
		if(!repository.existsById(rankingId)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(rankingId);
		
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<List<RankingDTO>> calcularRanking(MultipartFile[] files, Integer week) {
		rankingCalculator.calculate(files, week).forEach(this::create);
        	
		return ResponseEntity.ok(this.listar());
	}

	public void resetar() {
		repository.deleteAll();
	}

	public ResponseEntity<List<RankingDTO>> calcularRankingCsv(MultipartFile file) {
		rankingCalculator.calculateCsv(file).forEach(this::create);
    	
		return ResponseEntity.ok(this.listar());
	}
}
