package com.kdmeubichinho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.services.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/pessoa")
@RequiredArgsConstructor
@CrossOrigin
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping()
	public Iterable<Pessoa> getAllPersons(){
		return pessoaService.getAllPersons();
	}

}
