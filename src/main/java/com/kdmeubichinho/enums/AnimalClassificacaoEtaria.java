package com.kdmeubichinho.enums;
import java.util.stream.*;
public enum AnimalClassificacaoEtaria {
	FILHOTE("Filhote"), 
	ADULTO("Adulto");
	
	private String descricao;
	
	private AnimalClassificacaoEtaria(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public static AnimalClassificacaoEtaria of(String descricao) {
		  return Stream.of(AnimalClassificacaoEtaria.values())
		    .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
		}

}
