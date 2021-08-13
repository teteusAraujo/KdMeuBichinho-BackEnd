package com.kdmeubichinho.controllers;

import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimalControllerTest {

    private AnimalController controller;

    @Autowired
    public AnimalControllerTest(AnimalController controller){
        this.controller = controller;
    }

    @BeforeAll
    public void setup(){
        Animal a = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        this.controller.addAnimal(a);
    }

    @Test
    @Order(1)
    void getAllAnimais() {
        List<Animal> allAnimals = (List<Animal>) this.controller.getAllAnimais();
        assertFalse(allAnimals.isEmpty());
    }

    @Test
    @Order(2)
    void getById() {
        List<Animal> allAnimals = (List<Animal>) this.controller.getAllAnimais();
        Optional<Animal> byId = this.controller.getById(allAnimals.get(allAnimals.size() - 1).getIdAnimal());
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(3)
    void addAnimal() {
        Animal a = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        Animal animal = this.controller.addAnimal(a);
        Optional<Animal> byId = this.controller.getById(animal.getIdAnimal());
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(4)
    void updateAnimal() {
        Animal a = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        Animal animal = this.controller.addAnimal(a);

        Animal update = this.controller.updateAnimal(animal.getIdAnimal(), animal);

        assertEquals("00000-000", update.getCep());
    }

    @Test
    @Order(5)
    void deleteAnimal() {
        Animal a = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        Animal animal = this.controller.addAnimal(a);
        List<Animal> allAnimals = (List<Animal>) this.controller.getAllAnimais();
        this.controller.deleteAnimal(allAnimals.get(allAnimals.size() - 1).getIdAnimal());
        Optional<Animal> byId = this.controller.getById(animal.getIdAnimal());
        assertFalse(byId.isPresent());
    }
}