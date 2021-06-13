package com.kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdmeubichinho.dto.EspecieRequestDTO;
import com.kdmeubichinho.entities.Especie;
import com.kdmeubichinho.repositories.EspecieRepository;


@Service
public class EspecieService {
	
	@Autowired
	EspecieRepository especieRepository;

	public Iterable<Especie> getAllSpecies() {
		return especieRepository.findAll();
	}

	public Optional<Especie> getSpeciesById(Integer id) {
		return especieRepository.findById(id);
	}

	public EspecieRequestDTO addSpecies(EspecieRequestDTO speciesRequestDTO) {
		especieRepository.save(speciesRequestDTO.build());
		return speciesRequestDTO;
	}

	public void deleteSpecies(Integer id) {
		especieRepository.deleteById(id);
	}

}
