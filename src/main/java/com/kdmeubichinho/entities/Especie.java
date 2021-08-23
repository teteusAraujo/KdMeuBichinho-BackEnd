package com.kdmeubichinho.entities;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kdmeubichinho.entities.generics.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Especie implements BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especie")
	private Integer idEspecie;
	
	@Column(nullable = false)
	private String nome;

	@Transient
	@Override
	@JsonIgnore
	public Integer getId() {
		return this.getIdEspecie();
	}
}
