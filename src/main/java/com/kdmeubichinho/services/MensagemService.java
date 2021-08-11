package com.kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.repositories.MensagemRepository;
import com.kdmeubichinho.repositories.PessoaRepository;

import com.kdmeubichinho.entities.Pessoa;

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
	
	public Mensagem addMessage(Mensagem message){
		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(message.getIdPessoa().getEmail());
		if(pessoa.isPresent()) {
			Integer pessoaId = pessoa.get().getIdPessoa();
			message.getIdPessoa().setIdPessoa(pessoaId);
		}		
		mensagemRepository.save(message);
		return message;
	}
	
	public Mensagem updateMessage(Integer idMessage, Mensagem dataMessage) throws Exception{
		Mensagem messageToUpdate = mensagemRepository.findById(idMessage)
				.orElseThrow(()-> new IllegalAccessException());
		
		if(!dataMessage.getMensagem().isEmpty()) messageToUpdate.setMensagem(dataMessage.getMensagem());
		
		mensagemRepository.save(messageToUpdate);
		return messageToUpdate;
	}

}
