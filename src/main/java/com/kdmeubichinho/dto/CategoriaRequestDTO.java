package com.kdmeubichinho.dto;

import com.kdmeubichinho.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {
	
private String classificacao;
	
	public Categoria build() {
		Categoria categoria = new Categoria()
				.setClassificacao(this.classificacao);
		return categoria;
	}

}
