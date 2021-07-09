package com.kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.repositories.MensagemRepository;
import com.kdmeubichinho.repositories.PessoaRepository;

@Service
public class MensagemService {
	@Autowired
	private MensagemRepository mensagemRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Iterable<Mensagem> getAllMessages(){
		return mensagemRepository.findAll();
	}
	public Optional<Mensagem> getMessageById(Integer id){
		return mensagemRepository.findById(id);
	}

}
