package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.dto.CategoriaRequestDTO;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.services.CategoriaService;


@RestController
@RequestMapping(path = "categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public Iterable<Categoria> getAllCategory() {
		return categoriaService.getAllCategory();
	}
	
	@GetMapping("/{id}")
	public Optional<Categoria> getCategoryById(@PathVariable Integer id) {
		return categoriaService.getCategoryById(id);
	}

	@PostMapping()
	public CategoriaRequestDTO addCategory(@RequestBody CategoriaRequestDTO categoryRequestDTO) {
		return categoriaService.addCategory(categoryRequestDTO);
	}


}
