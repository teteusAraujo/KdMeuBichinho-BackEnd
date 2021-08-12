package com.kdmeubichinho.controllers;

import com.kdmeubichinho.entities.Foto;
import com.kdmeubichinho.repositories.FotoRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.MultipartBodyBuilder;
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
class FotoControllerTest {

    private FotoController controller;
    private FotoRepository fotoRepository;

    @Autowired
    public FotoControllerTest(FotoController controller, FotoRepository fotoRepository){
        this.controller = controller;
        this.fotoRepository = fotoRepository;
    }

    @BeforeAll
    public void setup(){
        Foto foto = new Foto(1, "caminho-foto");
        this.fotoRepository.save(foto);
    }

    @Test
    @Order(1)
    void getFoto() {
        List<Foto> fotoList = (List<Foto>) this.controller.getFoto();
        assertFalse(fotoList.isEmpty());
    }

    @Test
    @Order(2)
    void getById() {
        List<Foto> fotoList = (List<Foto>) this.controller.getFoto();
        Optional<Foto> byId = this.controller.getById(fotoList.get(fotoList.size() - 1).getIdFoto());
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(3)
    void saveImg() {

    }

    @Test
    @Order(4)
    void updateFoto() {
        List<Foto> fotoList = (List<Foto>) this.controller.getFoto();
        Foto f = fotoList.get(fotoList.size() -1);
        Foto foto = this.controller.updateFoto(f.getIdFoto(), f);

        assertEquals(f.getCaminho(), foto.getCaminho());
    }

    @Test
    @Order(5)
    void deleteFoto() {
        Foto foto = new Foto(null, "caminho-foto");
        this.fotoRepository.save(foto);

        List<Foto> fotoList = (List<Foto>) this.controller.getFoto();
        this.controller.deleteFoto(fotoList.get(fotoList.size() - 1).getIdFoto());
        Optional<Foto> byId = this.controller.getById(fotoList.get(fotoList.size() - 1).getIdFoto());
        assertFalse(byId.isPresent());
    }
}