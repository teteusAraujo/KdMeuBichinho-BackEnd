package com.kdmeubichinho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.services.MensagemService;

@RestController
@RequestMapping(path = "/mensagem")
@CrossOrigin
public class MensagemController {
	
	@Autowired
	private MensagemService mensagemService;
	
	@GetMapping()
	public Iterable<Mensagem> getAllMessages(){
		return mensagemService.getAllMessages();
	}
}
