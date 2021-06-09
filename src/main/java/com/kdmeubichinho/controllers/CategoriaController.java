package com.kdmeubichinho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
