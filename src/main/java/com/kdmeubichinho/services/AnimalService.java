package com.kdmeubichinho.services;

import java.util.Optional;

import com.kdmeubichinho.enums.EnumException;
import com.kdmeubichinho.exception.ValidationException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdmeubichinho.converters.AnimalClassificacaoEtariaConverter;
import com.kdmeubichinho.converters.AnimalPorteConverter;
import com.kdmeubichinho.converters.AnimalSexoConverter;
import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.repositories.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;
	
	public Iterable<Animal> getAllAnimals(){
		return animalRepository.findAll();
	}
	
	public Optional<Animal> getById(Integer id){
		return animalRepository.findById(id);
	}
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}
	public Animal updateAnimal(Integer idAnimal, Animal dadosAnimal){
		
		AnimalPorteConverter porteAnimalConverter = new AnimalPorteConverter();
		AnimalClassificacaoEtariaConverter classificacaoEtariaAnimalConverter = new AnimalClassificacaoEtariaConverter();
		AnimalSexoConverter sexoAnimalConverter = new AnimalSexoConverter();
		
		Animal meuAnimal = animalRepository.findById(idAnimal)
				.orElseThrow(()-> new ValidationException(EnumException.ITEM_NAO_ENCONTRADO));
				
		if(dadosAnimal.getCastrado() != null) meuAnimal.setCastrado(dadosAnimal.getCastrado());
		if(dadosAnimal.getVacinado() != null) meuAnimal.setVacinado(dadosAnimal.getVacinado());
		if(dadosAnimal.getEspecie() != null) meuAnimal.setEspecie(dadosAnimal.getEspecie());
		if(!StringUtils.isBlank(dadosAnimal.getCep())) meuAnimal.setCep(dadosAnimal.getCep());
		if(!StringUtils.isBlank(dadosAnimal.getNome())) meuAnimal.setNome(dadosAnimal.getNome());
		if(null != dadosAnimal.getPorte()) meuAnimal.setPorte(porteAnimalConverter.convertToEntityAttribute(dadosAnimal.getPorte().getDescricao()));
		if(null != dadosAnimal.getClassificacaoEtaria()) meuAnimal.setClassificacaoEtaria(classificacaoEtariaAnimalConverter.convertToEntityAttribute(dadosAnimal.getClassificacaoEtaria().getDescricao()));
		if(null != dadosAnimal.getSexo()) meuAnimal.setSexo(sexoAnimalConverter.convertToEntityAttribute(dadosAnimal.getSexo().getDescricao()));
		
		animalRepository.save(meuAnimal);
		return meuAnimal;
	}
	public void deleteAnimal(Integer id) {
		animalRepository.deleteById(id);
	}

}