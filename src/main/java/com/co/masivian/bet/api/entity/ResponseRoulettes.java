package com.co.masivian.bet.api.entity;

import java.util.List;

public class ResponseRoulettes {
	
	private String message;
	private List<Roulette> listRoulettes;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Roulette> getListRoulettes() {
		return listRoulettes;
	}
	public void setListRoulettes(List<Roulette> listRoulettes) {
		this.listRoulettes = listRoulettes;
	}
	
}
