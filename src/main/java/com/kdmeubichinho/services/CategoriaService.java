package com.kdmeubichinho.services;

import java.util.List;
import java.util.Optional;

import com.kdmeubichinho.services.generics.RestBasicService;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdmeubichinho.dto.CategoriaRequestDTO;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.repositories.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements RestBasicService<Categoria, CategoriaRequestDTO> {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public Page<Categoria> getAll(Pageable page) {
		return categoriaRepository.findAll(page);
	}

	@Override
	public Optional<Categoria> getById(Integer id) {
		return categoriaRepository.findById(id);
	}

	@Override
	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}

	@Override
	public CategoriaRequestDTO save(CategoriaRequestDTO categoria) {
		categoriaRepository.save(categoria.build());
		return categoria;
	}

	@Override
	public void deleteById(Integer id) {
		categoriaRepository.deleteById(id);
	}
}
