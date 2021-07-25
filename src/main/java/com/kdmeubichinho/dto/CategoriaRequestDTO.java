package com.kdmeubichinho.dto;

import com.kdmeubichinho.dto.generics.ObjectDTO;
import com.kdmeubichinho.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO implements ObjectDTO {
	
private String classificacao;
	
	public Categoria build() {
		return new Categoria()
				.setClassificacao(this.classificacao);
	}

}
