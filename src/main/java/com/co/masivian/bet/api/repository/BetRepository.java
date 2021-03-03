package com.co.masivian.bet.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.masivian.bet.api.entity.Bet;


public interface BetRepository extends JpaRepository<Bet, Integer>{
	
	@Query(value = "SELECT * FROM bets b WHERE b.roulette = ?1", nativeQuery = true)
	List<Bet> getBetsForRoulettes(int id);
	

}
