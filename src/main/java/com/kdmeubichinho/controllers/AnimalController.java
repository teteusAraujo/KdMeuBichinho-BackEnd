package com.kdmeubichinho.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.services.AnimalService;


@RestController
@RequestMapping(path = "animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping()
    public Iterable<Animal> getAllAnimais() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Optional<Animal> getById(@PathVariable Integer id) {
        return animalService.getById(id);
    }

    @PostMapping("/cadastrar")
    public Animal addAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
        return animal;
    }

    @PutMapping("/{idAnimal}")
    public Animal updateAnimal(@PathVariable Integer idAnimal, @RequestBody Animal dadosAnimal) throws Exception {
        return animalService.updateAnimal(idAnimal, dadosAnimal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
    }
}