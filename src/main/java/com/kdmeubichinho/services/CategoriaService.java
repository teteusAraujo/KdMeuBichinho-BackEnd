package com.kdmeubichinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.kdmeubichinho.dto.CategoriaRequestDTO;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.repositories.CategoriaRepository;

public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public Iterable<Categoria> getAllCategory() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> getCategoryById(Integer id) {
		return categoriaRepository.findById(id);
	}

	public CategoriaRequestDTO addCategory(CategoriaRequestDTO categoryRequestDTO) {
		categoriaRepository.save(categoryRequestDTO.build());
		return categoryRequestDTO;
	}

	public void deleteCategory(Integer id) {
		categoriaRepository.deleteById(id);
	}


}
