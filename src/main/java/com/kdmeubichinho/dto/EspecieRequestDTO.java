package com.kdmeubichinho.dto;


import com.kdmeubichinho.entities.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecieRequestDTO {
	
	private String nome;
	
	public Especie build() {
		return new Especie()
				.setNome(this.nome);
	}

}
