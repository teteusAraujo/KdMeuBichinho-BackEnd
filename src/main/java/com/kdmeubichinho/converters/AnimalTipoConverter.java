package com.kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.kdmeubichinho.enums.AnimalTipo;



@Converter(autoApply = true)
public class AnimalTipoConverter implements AttributeConverter<AnimalTipo, String> {
	
	@Override
	public String convertToDatabaseColumn(AnimalTipo tipo) {
		if(tipo == null) {
			return null;
		}
		return tipo.getDescricao();
	}
	
	@Override
	public AnimalTipo convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnimalTipo.of(descricao);
	}


}
