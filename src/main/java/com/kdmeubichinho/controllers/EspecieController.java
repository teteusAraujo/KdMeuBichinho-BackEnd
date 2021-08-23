package com.kdmeubichinho.controllers;

import com.kdmeubichinho.controllers.generics.RestBasicController;
import com.kdmeubichinho.dto.EspecieRequestDTO;
import com.kdmeubichinho.entities.Especie;
import com.kdmeubichinho.services.EspecieService;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 838de23df24748ee8c02c17c0590a453f46a350c

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
