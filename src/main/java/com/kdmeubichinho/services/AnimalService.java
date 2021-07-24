package com.kdmeubichinho.services;

import java.util.Optional;

import com.kdmeubichinho.enums.EnumException;
import com.kdmeubichinho.exception.ValidationException;
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
	public Animal addAnimal(Animal animal) {
		animalRepository.save(animal);
		return animal;
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
		if(!dadosAnimal.getCep().isEmpty()) meuAnimal.setCep(dadosAnimal.getCep());
		if(!dadosAnimal.getNome().isEmpty()) meuAnimal.setNome(dadosAnimal.getNome());
		if(!dadosAnimal.getPorte().isEmpty()) meuAnimal.setPorte(porteAnimalConverter.convertToEntityAttribute(dadosAnimal.getPorte()));
		if(!dadosAnimal.getClassificacaoEtaria().isEmpty()) meuAnimal.setClassificacaoEtaria(classificacaoEtariaAnimalConverter.convertToEntityAttribute(dadosAnimal.getClassificacaoEtaria()));
		if(!dadosAnimal.getSexo().isEmpty()) meuAnimal.setSexo(sexoAnimalConverter.convertToEntityAttribute(dadosAnimal.getSexo()));
		
		animalRepository.save(meuAnimal);
		return meuAnimal;
	}
	public void deleteAnimal(Integer id) {
		animalRepository.deleteById(id);
	}

}