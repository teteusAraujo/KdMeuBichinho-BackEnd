package com.kdmeubichinho.services;

import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Categoria;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;
import com.kdmeubichinho.enums.AnuncioStatus;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnuncioServiceTest {

    private AnuncioService service;
    private PessoaService pessoaService;
    private Pessoa pessoa;

    @Autowired
    public AnuncioServiceTest(AnuncioService service, PessoaService pessoaService){
        this.service = service;
        this.pessoaService = pessoaService;
    }

    @BeforeAll
    public void setup(){
        Pessoa p = new Pessoa("nome", "setup", "cep", "celular", "senha");
        Anuncio a = new Anuncio(AnuncioStatus.ATIVO, new Date());

        Animal an = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        an.setNome("");

        this.pessoa = this.pessoaService.save(p);

        a.setIdPessoa(this.pessoa);
        a.setIdAnimal(an);

        this.service.save(a);
    }

    @Test
    void getFilteredAnnounce() {
        Iterable<Anuncio> cep = this.service.getFilteredAnnounce(
                PageRequest.of(0, 50),
                "cep",
                AnuncioStatus.ATIVO,
                AnimalSexo.MACHO,
                AnimalPorte.PEQUENO,
                AnimalClassificacaoEtaria.ADULTO,
                1,
                1,
                true,
                true
        );

        assertNotNull(cep);
    }

    @Test
    void getAnnounceById() {
        this.addAnnounce();
        List<Anuncio> all = this.service.getAll();
        Optional<Anuncio> announceById = this.service.getAnnounceById(all.get(all.size() - 1).getIdAnuncio());
        assertTrue(announceById.isPresent());
    }

    @Test
    void getAnnounceByEmailPessoa() {
        Page<Anuncio> byEmailPessoa = this.service.getAnnounceByEmailPessoa("setup", PageRequest.of(0, 50));
        assertFalse(byEmailPessoa.isEmpty());
    }

    @Test
    void save() {
        Pessoa p = new Pessoa("save", "save" + Math.random(), "save", "save", "save");
        Anuncio a = new Anuncio(AnuncioStatus.ATIVO, new Date());

        Animal an = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        an.setNome("save");

        this.pessoa = this.pessoaService.save(p);

        a.setIdPessoa(this.pessoa);
        a.setIdAnimal(an);

        Anuncio save = this.service.save(a);

        assertEquals(AnuncioStatus.ATIVO, save.getStatus());
    }

    @Test
    void updateStatusAnnounce() {
        this.addAnnounce();
        List<Anuncio> all = this.service.getAll();
        Anuncio anuncio = service.updateStatusAnnounce(all.size() - 1);
        assertEquals(AnuncioStatus.INATIVO, anuncio.getStatus());

        anuncio = service.updateStatusAnnounce(all.size() - 1);
        assertEquals(AnuncioStatus.ATIVO, anuncio.getStatus());
    }

    @Test
    void deleteAnnounce() {
        List<Anuncio> all = this.service.getAll();
        this.service.deleteAnnounce(all.get(all.size() - 1).getIdAnuncio());
        Optional<Anuncio> announceById = this.service.getAnnounceById(all.get(all.size() - 1).getIdAnuncio());
        assertFalse(announceById.isPresent());
    }

    private void addAnnounce(){
        Pessoa p = new Pessoa("nome", "email" + Math.random(), "cep", "celular", "senha");
        Anuncio a = new Anuncio(AnuncioStatus.ATIVO, new Date());

        Animal an = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        an.setNome("Rex");

        this.pessoa = this.pessoaService.save(p);

        a.setIdPessoa(this.pessoa);
        a.setIdAnimal(an);

        this.service.save(a);
    }
}