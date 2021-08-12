package com.kdmeubichinho.entities;

import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Integer idAnimal;
	@Column(nullable = false)
	private AnimalSexo sexo;
	@Column(nullable = false)
	private AnimalClassificacaoEtaria classificacaoEtaria;
	@Column(nullable = false)
	private AnimalPorte porte;
	@Column(nullable = false)
	private Boolean castrado;
	@Column(nullable = false)
	private Boolean vacinado;
	@Column(nullable = true)
	private String nome;
	@Column(nullable = false)
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String ddd;
	@OneToOne()
	@JoinColumn(name = "fk_id_especie")
	private Especie especie;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_foto")
	private Foto fotos;

	public Animal(AnimalSexo animalSexo, AnimalClassificacaoEtaria classificacaoEtaria, AnimalPorte animalPorte,
				  Boolean castrado, Boolean vacinado, String cep){
		sexo = animalSexo;
		this.classificacaoEtaria = classificacaoEtaria;
		porte = animalPorte;
		this.castrado = castrado;
		this.vacinado = vacinado;
		this.cep = cep;
	}

}