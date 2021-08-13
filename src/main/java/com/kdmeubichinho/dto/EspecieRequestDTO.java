package com.kdmeubichinho.dto;


import com.kdmeubichinho.dto.generics.ObjectDTO;
import com.kdmeubichinho.entities.Especie;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecieRequestDTO implements ObjectDTO {
	
	private String nome;
	
	public Especie build() {
		return new Especie()
				.setNome(this.nome);
	}

}
