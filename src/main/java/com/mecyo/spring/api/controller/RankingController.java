package com.mecyo.spring.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mecyo.spring.api.dto.RankingDTO;
import com.mecyo.spring.domain.service.RankingService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ranking")
public class RankingController {

	private RankingService service;
	

	@GetMapping
	public List<RankingDTO> listar() {
		return service.listar();
	}

	@PostMapping("/calcular")
	public ResponseEntity<List<RankingDTO>> calcularRanking(@RequestParam("files") MultipartFile[] files, @RequestParam Integer week) {
		return service.calcularRanking(files, week);
	}
	
	@PostMapping("/calcular-csv")
	public ResponseEntity<List<RankingDTO>> calcularRankingCsv(@RequestParam("file") MultipartFile file) {
		return service.calcularRankingCsv(file);
	}
	
	@GetMapping("/resetar")
	public ResponseEntity<Void> resetar() {
		service.resetar();
		return ResponseEntity.noContent().build();
	}
}
