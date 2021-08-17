package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.dto.EspecieRequestDTO;
import com.kdmeubichinho.entities.Especie;
import com.kdmeubichinho.services.EspecieService;

@RestController
@RequestMapping(path = "especie")
public class EspecieController {

	@Autowired
	EspecieService especieService;

	@GetMapping
	public Iterable<Especie> getAllSpecies() {
		return especieService.getAllSpecies();
	}

	@GetMapping("/{id}")
	public Optional<Especie> getSpeciesById(@PathVariable Integer id) {
		return especieService.getSpeciesById(id);
	}
	
	@PostMapping()
	public EspecieRequestDTO addSpecies(@RequestBody EspecieRequestDTO speciesRequestDTO) {
		return especieService.addSpecies(speciesRequestDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSpecies(@PathVariable Integer id) {
		especieService.deleteSpecies(id);
	}

}
