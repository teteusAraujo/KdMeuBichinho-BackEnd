package com.kdmeubichinho.controllers;

import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    /*
    FIXME Fazer este metodo retonar a representacao mais atualida deste objeto
     */
    @PostMapping("/cadastrar")
    public Animal addAnimal(@RequestBody Animal animal) {
        animalService.save(animal);
        return animal;
    }

    @PutMapping("/{idAnimal}")
    public Animal updateAnimal(@PathVariable Integer idAnimal, @RequestBody Animal dadosAnimal) {
        return animalService.updateAnimal(idAnimal, dadosAnimal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
    }
}