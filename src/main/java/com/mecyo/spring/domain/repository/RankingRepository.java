package com.mecyo.spring.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mecyo.spring.domain.model.Ranking;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
	
	public Optional<Ranking> findById(Long id);
	
	public Page<Ranking> findByNicknameContaining(String nickname, Pageable page);

	public Ranking findByNickname(String nickname);
}
