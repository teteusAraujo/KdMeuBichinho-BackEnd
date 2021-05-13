package com.kdmeubichinho.enums;

import java.util.stream.Stream;

public enum AnimalPorte{
	PEQUENO("Pequeno"), 
	MEDIO("Médio"),
	GRANDE("Grande");
	
	private String descricao;
	
	private AnimalPorte(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public static AnimalPorte of(String descricao) {
		  return Stream.of(AnimalPorte.values())
		    .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
	}	
}