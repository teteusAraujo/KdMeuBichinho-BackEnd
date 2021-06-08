package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;
import com.kdmeubichinho.enums.AnuncioStatus;
import com.kdmeubichinho.services.AnuncioService;

@RestController
@RequestMapping(path = "/anuncio")
@CrossOrigin
public class AnuncioController {
	
	@Autowired
	private AnuncioService anuncioService;
	
	@GetMapping("/busca")
	public Iterable<Anuncio> getFilteredAnnounce(Pageable pageable, String cep, AnuncioStatus status, AnimalSexo sexo,
			AnimalPorte porte, AnimalClassificacaoEtaria classificacaoEtaria, Integer idCategoria, Integer idEspecie,
			Boolean castrado, Boolean vacinado) {
		return anuncioService.getFilteredAnnounce(pageable, cep, status, sexo, porte, classificacaoEtaria, idCategoria,
				idEspecie, castrado, vacinado);
	}
	
	@GetMapping("/{id}")
	public Optional<Anuncio> getAnnounceById(@PathVariable Integer id) {
		return anuncioService.getAnnounceById(id);
	}

	@GetMapping("/pessoa")
	public Page<Anuncio> getAnnounceByEmailPessoa(@RequestParam String email, Pageable pageable) {
		return anuncioService.getAnnounceByEmailPessoa(email, pageable);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Anuncio addAnnounce(@RequestBody Anuncio anuncio) {
		return anuncioService.addAnnounce(anuncio);
	}

	@PutMapping("atualizastatus/{idAnuncio}")
	public Anuncio updateStatusAnnounce(@PathVariable Integer idAnuncio) throws Exception {
		return anuncioService.updateStatusAnnounce(idAnuncio);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAnnounce(@PathVariable Integer id) {
		anuncioService.deleteAnnounce(id);
	}
}
