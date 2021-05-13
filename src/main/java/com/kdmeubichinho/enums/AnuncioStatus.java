package com.kdmeubichinho.enums;

import java.util.stream.Stream;

public enum AnuncioStatus {
	ATIVO("Ativo"), 
	INATIVO("Inativo");
	
	private String descricao;
	
	private AnuncioStatus(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public static AnuncioStatus of(String descricao) {
		  return Stream.of(AnuncioStatus.values())
		    .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
		}
}