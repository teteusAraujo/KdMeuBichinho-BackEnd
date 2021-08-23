package com.kdmeubichinho.services;

import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.entities.Foto;
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
class AnimalServiceTest {

    private AnimalService service;

    @Autowired
    public AnimalServiceTest(AnimalService service) {
        this.service = service;
    }

    @BeforeAll
    public void setup() {
        Foto f = new Foto(1, "");
        Animal a = new Animal(1,
                AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO, true,
                true, "animal", "00000-000", "logradouro", "complemento",
                "bairro", "localidade", "uf", "ibge", "ddd", null, f);

        this.service.save(a);
    }

    @Test
    @Order(1)
    void getAllAnimals() {
        List<Animal> allAnimals = (List<Animal>) this.service.getAllAnimals();
        assertFalse(allAnimals.isEmpty());
    }

    @Test
    @Order(2)
    void getById() {
        List<Animal> allAnimals = (List<Animal>) this.service.getAllAnimals();
        Optional<Animal> byId = this.service.getById(allAnimals.get(allAnimals.size() - 1).getIdAnimal());
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(3)
    void save() {
        Foto f = new Foto(1, "");
        Animal a = new Animal(5,
                AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO, true,
                true, "save-animal", "00000-000", "logradouro", "complemento",
                "bairro", "localidade", "uf", "ibge", "ddd", null, f);

        Animal save = this.service.save(a);
        assertEquals("save-animal", save.getNome());
    }

    @Test
    @Order(4)
    void updateAnimal() {
        List<Animal> allAnimals = (List<Animal>) this.service.getAllAnimals();
        Animal a = allAnimals.get(allAnimals.size() - 1);
        Animal animal = this.service.updateAnimal(a.getIdAnimal(), a);
        assertEquals(a.getNome(), animal.getNome());
    }

    @Test
    @Order(5)
    void deleteAnimal() {
        Animal a = new Animal(null,
                AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO, true,
                true, "save-animal", "00000-000", "logradouro", "complemento",
                "bairro", "localidade", "uf", "ibge", "ddd", null, null);

        Animal save = this.service.save(a);

        this.service.deleteAnimal(save.getIdAnimal());
        Optional<Animal> byId = this.service.getById(save.getIdAnimal());
        assertFalse(byId.isPresent());
    }
}