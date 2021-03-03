package com.co.masivian.bet.api.entity;

import java.util.List;

public class ResponseCloseBet {
	
	private String numberWinner;
	private List<Bet> listBets;
	
	public String getNumberWinner() {
		return numberWinner;
	}
	public void setResult(String numberWinner) {
		this.numberWinner = numberWinner;
	}
	public List<Bet> getListBets() {
		return listBets;
	}
	public void setListBets(List<Bet> listBets) {
		this.listBets = listBets;
	}
	
	

}
