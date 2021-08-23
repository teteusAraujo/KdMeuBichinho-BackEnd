package com.kdmeubichinho.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.kdmeubichinho.entities.generics.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Mensagem implements BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mensagem")
	private Integer idMensagem;
	
	@Column(name = "data_mensagem", nullable = false)
	private Date dataMensagem;
	
	@Column(nullable = false, name = "mensagem")
	private String msgConteudo;
	
	@JoinColumn(name = "fk_id_pessoa")
	@OneToOne
	private Pessoa idPessoa;
	
	@JoinColumn(name = "fk_id_anuncio")
	@ManyToOne
	@JsonIgnoreProperties("mensagens")
	private Anuncio idAnuncio;

	@Override
	public Integer getId(){
		return this.getIdMensagem();
	}
}
