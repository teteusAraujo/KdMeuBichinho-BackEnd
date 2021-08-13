package com.kdmeubichinho.controllers;

import com.kdmeubichinho.dto.CategoriaRequestDTO;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.entities.generics.BaseEntity;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
class CategoriaControllerTest {

    private CategoriaController controller;
    private CategoriaRequestDTO dto = new CategoriaRequestDTO("Categoria de Teste");

    @Autowired
    public CategoriaControllerTest(CategoriaController controller) {
        this.controller = controller;
    }

    @BeforeAll
    void setup() {
        for (int i = 0; i < 60; i++) {
            this.controller.save(dto);
        }
    }

    @AfterAll
    void rollback() {
        this.controller.getAll()
                .forEach(item -> this.controller.delete(((BaseEntity) item).getId()));
    }

    @Test
    @Order(1)
    void getAllPaged() {
        Page<Categoria> all = this.controller.getAllPaged(PageRequest.of(0, 50));
        assertEquals(50, all.stream().count());
    }

    @Test
    @Order(2)
    void getAll() {
        List<Categoria> all = this.controller.getAll();
        assertEquals(60, all.size());
    }

    @Test
    @Order(3)
    void getById() {
        Optional<Categoria> byId = this.controller.getById(1);
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(4)
    void save() {
        this.controller.save(dto);
        List<Categoria> all = this.controller.getAll();
        assertEquals(61, all.size());
    }

    @Test
    @Order(5)
    void delete() {
        BaseEntity primeiro = (BaseEntity) this.controller.getAll().get(0);
        controller.delete(primeiro.getId());
        Optional<Categoria> byId = controller.getById(primeiro.getId());
        assertFalse(byId.isPresent());
    }

}