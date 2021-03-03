package com.co.masivian.bet.api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.masivian.bet.api.entity.Roulette;
import com.co.masivian.bet.api.service.IRouletteService;

@RestController
@RequestMapping("/api")
public class RouletteController {
	
	@Autowired
	private IRouletteService serviceRoulette;
	
	@GetMapping("/createRoulette")
	public int create() {
		Roulette roulette = new Roulette();
		roulette.setState("");
		Roulette rouletteCreated = new Roulette();
		rouletteCreated = serviceRoulette.create(roulette);
		if(String.valueOf(rouletteCreated.getId()).equals(null)) {
			return 0;
		}
		return rouletteCreated.getId();
		
	}
	
	@PostMapping("/openRoulette/{id}")
	public String open(@PathVariable("id") int id) {
		Roulette roulette = new Roulette();
		roulette = serviceRoulette.search(id);
		System.out.println(roulette.getId());
		if(roulette.getId() == 0) {
			System.out.println("Ruleta no encontrada");
			return "La ruleta a abrir no existe";
		}
		
		return serviceRoulette.open(roulette);
		
	}
	

}
