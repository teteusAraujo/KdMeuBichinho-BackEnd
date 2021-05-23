package com.kdmeubichinho.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Integer idPessoa;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String email;
	
	@Column(nullable = false)
    private String cep;
	
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String ddd;
    
    @Column(name = "numero_residencial")
    private String numeroResidencial;
    
    @Column(nullable = false)
    private String celular;
    
    @Column(nullable = false)
    private String senha;
    
    private boolean admin;
    
    public Pessoa(Integer idPessoa, String nome, String email, String cep, String logradouro, String complemento, String bairro, 
    		String localidade, String uf, String ibge, String ddd, String numeroResidencial, String celular, String senha) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
		this.ddd = ddd;
		this.numeroResidencial = numeroResidencial;
		this.celular = celular;
		this.senha = senha;
	}


}
