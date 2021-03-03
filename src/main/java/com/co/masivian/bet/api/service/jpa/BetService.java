package com.co.masivian.bet.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.masivian.bet.api.entity.Bet;
import com.co.masivian.bet.api.entity.ResponseBet;
import com.co.masivian.bet.api.entity.Roulette;
import com.co.masivian.bet.api.entity.Users;
import com.co.masivian.bet.api.repository.BetRepository;
import com.co.masivian.bet.api.repository.RouletteRepository;
import com.co.masivian.bet.api.repository.UserRepository;
import com.co.masivian.bet.api.service.IBetService;

@Service
public class BetService implements IBetService {

	@Autowired
	private RouletteRepository repoRoulette;

	@Autowired
	private BetRepository repoBet;

	@Autowired
	private UserRepository repoUser;

	@Override
	public ResponseBet create(Bet bet) {
		ResponseBet response = new ResponseBet();
		Users usu = new Users();
		Optional<Roulette> rouletteFinded = repoRoulette.findById(bet.getId_roulette().getId());
		if (rouletteFinded.isPresent()) {
			if (rouletteFinded.get().getState().equalsIgnoreCase("Abierta")) {
				response.setMessage("Apuesta creada exitosamente");
				response.setBet(bet);
				String respuesta = bet.toString();
				System.out.println("Objeto:" + respuesta);
				repoBet.save(bet);
				usu = bet.getId_usu();
				Double newCredit = Double.parseDouble(usu.getCredit()) - bet.getAmount();
				usu.setCredit(String.valueOf(newCredit));
				repoUser.save(usu);
				return response;
			}
			if (rouletteFinded.get().getState().equalsIgnoreCase("Cerrada")) {
				response.setMessage("La ruleta se encuentra cerrada");
				return response;
			}
			response.setMessage("La ruleta no ha sido abierta");
			return response;

		} else {
			response.setMessage("La ruleta no existe");
			return response;
		}

	}

	@Override
	public Users search(int id) {
		Optional<Users> user = repoUser.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;

	}

	@Override
	public List<Bet> findBetsForRoulettes(int id) {
		return repoBet.getBetsForRoulettes(id);
	}

	@Override
	public void update(Bet bet) {
		Optional<Bet> betFinded = repoBet.findById(bet.getId());
		if(betFinded.isPresent()) {
			repoBet.save(bet);
		}
		
	}

}
