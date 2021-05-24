package com.kdmeubichinho.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.kdmeubichinho.enums.AnimalSexo;

@Converter(autoApply = true)
public class AnimalSexoConverter implements AttributeConverter<AnimalSexo, String>{
	
	@Override
	public String convertToDatabaseColumn(AnimalSexo sexo) {
		if(sexo == null) {
			return null;
		}
		return sexo.getDescricao();
	}
	
	@Override
	public AnimalSexo convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		return AnimalSexo.of(descricao);
	}


}
