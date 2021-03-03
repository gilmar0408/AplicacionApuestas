package com.co.masivian.bet.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bets")
public class Bet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name="users")
	private Users users;
	@OneToOne
	@JoinColumn(name="roulette")
	private Roulette roulette;
	private int number_bet;
	private String color;
	private double amount;
	private String result;
	
	public boolean isValid() {
		if (String.valueOf(this.number_bet) == null || String.valueOf(this.number_bet).isEmpty() && this.color == null
				|| this.color.isEmpty()) {
			return false;
		}
		if (String.valueOf(this.number_bet) == null || String.valueOf(this.number_bet).isEmpty()) {
			if (!this.color.equalsIgnoreCase("Negro") || !this.color.equalsIgnoreCase("Rojo")) {
				return false;
			}
		}
		if (this.color == null || this.color.isEmpty()) {
			if (this.number_bet < 0 || this.number_bet > 36) {
				return false;
			}
		}
		if (String.valueOf(this.amount) == null || String.valueOf(this.amount).isEmpty()) {
			return false;
		}
		if (this.amount > 10000) {
			return false;
		}
		if(String.valueOf(this.roulette.getId()) == null || String.valueOf(this.roulette.getId()).isEmpty()) {
			return false;
		}

		return true;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getId_usu() {
		return users;
	}
	public void setId_usu(Users id_usu) {
		this.users = id_usu;
	}
	public Roulette getId_roulette() {
		return roulette;
	}
	public void setId_roulette(Roulette id_roulette) {
		this.roulette = id_roulette;
	}
	public int getNumber_bet() {
		return number_bet;
	}
	public void setNumber_bet(int number_bet) {
		this.number_bet = number_bet;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Bet [id=" + id + ", id_usu=" + users + ", id_roulette=" + roulette + ", number_bet=" + number_bet
				+ ", color=" + color + ", amount=" + amount + ", result=" + result + "]";
	}
	
	
	
}
