package com.co.masivian.bet.api.service.jpa;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.masivian.bet.api.entity.Roulette;
import com.co.masivian.bet.api.repository.RouletteRepository;
import com.co.masivian.bet.api.service.IRouletteService;

@Service
public class RouletteService implements IRouletteService{
	
	@Autowired
	private RouletteRepository repoRoulette;
	
	@Override
	public Roulette create(Roulette roulette) {
		return repoRoulette.save(roulette);
		
	}

	@Override
	public String open(Roulette roulette) {
		Roulette rouletteCreated = new Roulette();
		roulette.setState("Abierta");
		rouletteCreated = repoRoulette.save(roulette);
		String mesaje = "Ruleta no pudo ser abierta";
		if(rouletteCreated == null) {
			return mesaje;
		}
		mesaje = "Ruleta abierta exitosamente";
		return mesaje;
	}

	@Override
	public String close(Roulette roulette) {
		Roulette rouletteCreated = new Roulette();
		roulette.setState("Cerrada");
		rouletteCreated = repoRoulette.save(roulette);
		String message = "Ruleta no pudo ser cerrada";
		if(rouletteCreated == null) {
			return message;
		}
		message = "Ruleta cerrada exitosamente";
		return message;
		
	}

	@Override
	public Roulette search(int id) {
		Optional<Roulette> rouletteFiended = repoRoulette.findById(id);
		if(rouletteFiended.isPresent()) {
			Roulette roulette = new Roulette();
			roulette = rouletteFiended.get();
			return roulette;
		}else {
			System.out.println("No encontrado");
			Roulette roulette = new Roulette();
			return roulette;
		}
		
	}
	
	



	
}
