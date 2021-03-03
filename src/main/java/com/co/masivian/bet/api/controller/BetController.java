package com.co.masivian.bet.api.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.masivian.bet.api.entity.Bet;
import com.co.masivian.bet.api.entity.RequestBet;
import com.co.masivian.bet.api.entity.ResponseBet;
import com.co.masivian.bet.api.entity.ResponseCloseBet;
import com.co.masivian.bet.api.entity.Roulette;
import com.co.masivian.bet.api.entity.Users;
import com.co.masivian.bet.api.service.IBetService;
import com.co.masivian.bet.api.service.IRouletteService;
import com.co.masivian.bet.api.service.IUserService;

@RestController
@RequestMapping("/api")
public class BetController {

	@Autowired
	private IBetService serviceBet;

	@Autowired
	private IRouletteService serviceRoulette;

	@Autowired
	private IUserService serviceUser;

	@PostMapping("/createBet")
	public ResponseBet create(@RequestBody RequestBet request) {
		ResponseBet response = new ResponseBet();
		if (!request.isValid()) {
			response.setMessage("Parametros Invalidos, por favor valide de acuerdo a requerimientos");
			return response;
		}

		Roulette roulette = new Roulette();
		Users user = new Users();
		roulette = serviceRoulette.search(Integer.parseInt(request.getIdRuleta()));
		if (roulette == null) {
			response.setMessage("La ruleta no se encuentra creada");
			return response;
		}
		user = serviceBet.search(Integer.parseInt(request.getIdUser()));
		if (user == null) {
			response.setMessage("El usuario no se encuentra creado");
			return response;
		}
		Bet bet = new Bet();
		bet.setId_usu(user);
		bet.setId_roulette(roulette);
		if (!request.getNumber().equals("")) {
			bet.setNumber_bet(Integer.parseInt(request.getNumber()));
		}
		bet.setNumber_bet(0);
		bet.setColor(request.getColor());
		bet.setAmount(Integer.parseInt(request.getAmount()));
		bet.setResult("");

		return serviceBet.create(bet);
	}

	@PostMapping("/closeBet/{id}")
	public ResponseCloseBet close(@PathVariable("id") int id) {
		ResponseCloseBet response = new ResponseCloseBet();
		Random random = new Random();
		Users user = new Users();
		Roulette roulette = new Roulette();
		List<Bet> listBets = serviceBet.findBetsForRoulettes(id);
		if (listBets.size() == 0) {
			System.out.println("No retorna informacion");
			response.setResult("La apuesta a cerrar no existe");
			return response;
		}
		int numberWinner = random.nextInt(36 + 1) + 1;
		for (int i = 0; i < listBets.size(); i++) {
			if (listBets.get(i).getNumber_bet() == numberWinner) {
				user = serviceUser.search(listBets.get(i).getId_usu().getId_user());
				if (!user.equals(null)) {
					Double actualCredit = Double.parseDouble(user.getCredit());
					user.setCredit(String.valueOf(actualCredit + (listBets.get(i).getAmount() * 5)));
					serviceUser.update(user);
				}
			}
			serviceBet.update(listBets.get(i));
		}
		serviceRoulette.close(listBets.get(0).getId_roulette());
		response.setResult(String.valueOf(numberWinner));
		response.setListBets(listBets);
		return response;

	}

}
