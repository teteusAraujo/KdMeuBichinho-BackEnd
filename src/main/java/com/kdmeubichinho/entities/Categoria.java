package com.kdmeubichinho.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kdmeubichinho.entities.generics.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Categoria implements BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Integer idCategoria;
	
	@Column(nullable = false)
	private String classificacao;

	@Transient
	@Override
	@JsonIgnore
	public Integer getId() {
		return this.getIdCategoria();
	}
}
