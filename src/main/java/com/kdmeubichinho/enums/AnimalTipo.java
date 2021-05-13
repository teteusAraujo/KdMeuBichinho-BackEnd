package com.kdmeubichinho.enums;

import java.util.stream.Stream;

public enum AnimalTipo {
	CACHORRO("Cachorro"), GATO("Gato"), COELHO("Coelho"), HAMSTER("Hamster");
	
	private String descricao;

	private AnimalTipo(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static AnimalTipo of(String descricao) {
		  return Stream.of(AnimalTipo.values())
		    .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
		}
	
}