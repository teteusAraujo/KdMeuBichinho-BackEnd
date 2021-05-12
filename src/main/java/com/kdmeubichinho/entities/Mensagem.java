package com.kdmeubichinho.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Mensagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mensagem")
	private Integer idMensagem;
	@Column(name = "data_mensagem", nullable = false)
	private Date dataMensagem;
	@Column(nullable = false)
	private String mensagem;
	@JoinColumn(name = "fk_id_pessoa")
	@OneToOne
	private Pessoa idPessoa;
	@JoinColumn(name = "fk_id_anuncio")
	@ManyToOne
	@JsonIgnoreProperties("mensagens")
	private Anuncio idAnuncio;	
}
