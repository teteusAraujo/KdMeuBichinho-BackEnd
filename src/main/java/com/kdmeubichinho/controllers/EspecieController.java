package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.entities.Especie;


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

}
