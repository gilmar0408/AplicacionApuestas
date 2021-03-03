package com.co.masivian.bet.api.entity;

public class RequestBet {
	
	private String idUser;
	private String idRuleta;
	private String number;
	private String color;
	private String amount;
	
	public boolean isValid() {
		if (this.idUser == null || this.idUser.isEmpty() || this.idRuleta == null || this.idRuleta.isEmpty()
				|| this.amount == null || this.amount.isEmpty()) {
			System.out.println("Campos vacios o nulos");
			return false;
		}
		if ((this.number == null || this.number.isEmpty()) && (this.color == null || this.color.isEmpty())) {
			System.out.println("Numero y Color vacios o nulos");
			return false;
		}
		if (this.number == null || this.number.isEmpty()) {
			System.out.println("Campo number es vacio o nulo");
			if (!this.color.equalsIgnoreCase("Negro") && !this.color.equalsIgnoreCase("Rojo")) {
				System.out.println("Campo color no es rojo ni negro");
				return false;
			}
		}
		if (this.color == null || this.color.isEmpty()) {
			System.out.println("Campo color es vacio o nulo");
			if (Integer.parseInt(this.number) < 0 || Integer.parseInt(this.number) > 36) {
				System.out.println("Campo numero es inferior a 0 y superior a 36");
				return false;
			}
		}

		if (Double.parseDouble(this.amount) > 10000) {
			System.out.println("Campo amount es superior a 10000");
			return false;
		}

		return true;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdRuleta() {
		return idRuleta;
	}

	public void setIdRuleta(String idRuleta) {
		this.idRuleta = idRuleta;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
