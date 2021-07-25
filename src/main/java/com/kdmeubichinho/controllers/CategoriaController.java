package com.kdmeubichinho.controllers;

import com.kdmeubichinho.controllers.generics.RestBasicController;
import com.kdmeubichinho.dto.CategoriaRequestDTO;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "categoria")
public class CategoriaController extends RestBasicController<Categoria, CategoriaRequestDTO> {

	CategoriaService categoriaService;

	@Autowired
	public CategoriaController(CategoriaService service) {
		super(service);
		this.categoriaService = service;
	}
}
