package com.kdmeubichinho.controllers;

import com.kdmeubichinho.controllers.generics.RestBasicController;
import com.kdmeubichinho.dto.EspecieRequestDTO;
import com.kdmeubichinho.entities.Especie;
import com.kdmeubichinho.services.EspecieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "especie")
public class EspecieController extends RestBasicController<Especie, EspecieRequestDTO> {

    @Autowired
    EspecieService especieService;

    @Autowired
    public EspecieController(EspecieService service) {
        super(service);
        this.especieService = service;
    }

}
