package org.raflab.studsluzba.controllers.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.model.entities.Predmet;
import org.raflab.studsluzba.services.PredmetService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/predmet")
public class PredmetController {
	
	final PredmetService predmetService;
	
	@GetMapping(path = "/all")
	public Iterable<Predmet> getPredmeti(){
		return predmetService.findAll();
	}
	
	@GetMapping(path = "/all/{godinaAkreditacije}")
	public List<Predmet> getPredmetiForGodinaAkreditacije(@PathVariable Integer godinaAkreditacije){
		return predmetService.getPredmetForGodinaAkreditacije(godinaAkreditacije);
	}
	
}
