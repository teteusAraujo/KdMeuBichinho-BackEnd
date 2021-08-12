package com.kdmeubichinho.services;

import java.util.List;
import java.util.Optional;

import com.kdmeubichinho.dto.MensagemDTO;
import com.kdmeubichinho.services.generics.RestBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.repositories.MensagemRepository;
import com.kdmeubichinho.repositories.PessoaRepository;

import com.kdmeubichinho.entities.Pessoa;

@Service
public class MensagemService implements RestBasicService<Mensagem, MensagemDTO> {

	private MensagemRepository mensagemRepository;

	private PessoaRepository pessoaRepository;

	@Autowired
	public MensagemService(MensagemRepository mensagemRepository, PessoaRepository pessoaRepository) {
		this.mensagemRepository = mensagemRepository;
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public List<Mensagem> getAll() {
		return (List<Mensagem>) mensagemRepository.findAll();
	}

	@Override
	public Page<Mensagem> getAll(Pageable page) {
		return mensagemRepository.findAll(page);
	}

	@Override
	public Optional<Mensagem> getById(Integer id) {
		return mensagemRepository.findById(id);
	}

	@Override
	public MensagemDTO save(MensagemDTO i) {
		return null;
	}

	public Mensagem save(Mensagem message){
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
		
		if(!dataMessage.getMsgConteudo().isEmpty()) messageToUpdate.setMsgConteudo(dataMessage.getMsgConteudo());
		
		mensagemRepository.save(messageToUpdate);
		return messageToUpdate;
	}

	@Override
	public void deleteById(Integer id) {
		this.mensagemRepository.deleteById(id);
	}
}
