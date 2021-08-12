package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public Optional<Mensagem> getMessageById(@PathVariable Integer id){
		return mensagemService.getMessageById(id);
	}
	
	@PostMapping()
	public Mensagem addMessage(@RequestBody Mensagem message){
		return mensagemService.addMessage(message);
	}
	
	@PutMapping("/{idMessage}")
	public Mensagem updateMessage(@PathVariable Integer idMessage,@RequestBody Mensagem dataMessage) throws Exception{
		return mensagemService.updateMessage(idMessage, dataMessage);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMessage(@PathVariable Integer id) {
		mensagemService.deleteMessage(id);
	} 
}
