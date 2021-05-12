package com.kdmeubichinho.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_foto;
	@Column(nullable = false)
	private String caminho;
//	@OneToOne
//	@JoinColumn(name = "fk_id_animal")
//	@JsonIgnoreProperties("fotos")
//	private Animal idAnimal;
	
}
