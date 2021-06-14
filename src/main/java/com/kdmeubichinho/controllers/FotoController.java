package com.kdmeubichinho.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kdmeubichinho.entities.Foto;
import com.kdmeubichinho.repositories.FotoRepository;

@RestController
@RequestMapping(path = "foto")
public class FotoController {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@GetMapping()
	public Iterable<Foto> getFoto(){
		return fotoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Foto> getById(@PathVariable Integer id){
		return fotoRepository.findById(id);
	}
}
