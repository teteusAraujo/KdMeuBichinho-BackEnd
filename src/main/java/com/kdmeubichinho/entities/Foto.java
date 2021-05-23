package com.kdmeubichinho.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_foto;
	
	@Column(nullable = false)
	private String caminho;


	
}
