package com.mecyo.spring.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mecyo.spring.domain.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	public Optional<Player> findById(Long id);
	
	public Page<Player> findByNicknameContaining(String nickname, Pageable page);

	public Player findByNickname(String nickname);

	@Query("SELECT p FROM Player p WHERE p.dataBanimento IS NOT NULL")
	public Page<Player> findAllBanned(Pageable page);

	@Query("SELECT p FROM Player p WHERE p.dataBanimento IS NOT NULL AND p.nickname LIKE %:nickname%")
	public Page<Player> findBannedByNicknameContaining(String nickname, Pageable page);
}
