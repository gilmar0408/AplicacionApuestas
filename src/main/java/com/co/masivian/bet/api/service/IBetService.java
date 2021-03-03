package com.co.masivian.bet.api.service;

import java.util.List;

import com.co.masivian.bet.api.entity.Bet;
import com.co.masivian.bet.api.entity.ResponseBet;
import com.co.masivian.bet.api.entity.Users;

public interface IBetService {
	
	ResponseBet create(Bet bet);
	void update(Bet bet);
	Users  search(int id);
	List<Bet> findBetsForRoulettes(int id);

}
