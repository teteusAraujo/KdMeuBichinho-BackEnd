package com.kdmeubichinho.services;

import com.kdmeubichinho.dto.MensagemDTO;
import com.kdmeubichinho.entities.Animal;
import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.entities.generics.BaseEntity;
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
class MensagemServiceTest {

    private MensagemService service;
    private final PessoaService pessoaService;
    private final AnuncioService anuncioService;
    private AnimalService animalService;
    private Pessoa pessoa;
    private Anuncio anuncio;

    @Autowired
    public MensagemServiceTest(MensagemService service, PessoaService pessoaService, AnuncioService anuncioService,
                               AnimalService animalService){
        this.service = service;
        this.pessoaService = pessoaService;
        this.anuncioService = anuncioService;
        this.animalService = animalService;
    }

    @BeforeAll
    public void setup() {
        Pessoa p = new Pessoa("nome", "email", "cep", "celular", "senha");
        Anuncio a = new Anuncio(AnuncioStatus.ATIVO, new Date());
        Animal an = new Animal(AnimalSexo.MACHO, AnimalClassificacaoEtaria.ADULTO, AnimalPorte.PEQUENO,
                true, true, "00000-000");
        an.setNome("Rex");

        this.pessoa = this.pessoaService.save(p);

        a.setIdPessoa(this.pessoa);
        a.setIdAnimal(an);
        this.anuncio = this.anuncioService.save(a);

        for (int i = 0; i < 60; i++) {
            this.service.save(getMensagem());
        }
    }

    @AfterAll
    public void rollback() {
        this.service.getAll()
                .forEach(item -> this.service.deleteById(((BaseEntity)item).getId()));
    }

    @Test
    @Order(1)
    void getAllPaged() {
        Page<Mensagem> all = this.service.getAll(PageRequest.of(0, 50));
        assertEquals(50, all.stream().count());
    }

    @Test
    @Order(2)
    void getAll() {
        List<Mensagem> all = this.service.getAll();
        assertEquals(60, all.size());
    }

    @Test
    @Order(3)
    void getById() {
        Optional<Mensagem> byId = this.service.getById(1);
        assertTrue(byId.isPresent());
    }

    @Test
    @Order(4)
    void save() {
        Mensagem mensagem = getMensagem();
        this.service.save(mensagem);
        List<Mensagem> all = this.service.getAll();
        assertEquals(61, all.size());
    }

    @Test
    @Order(4)
    void saveWithNullParameter() {
        MensagemDTO save = this.service.save(new MensagemDTO());
        assertNull(save);
    }

    @Test
    @Order(5)
    void delete() {
        BaseEntity primeiro = (BaseEntity) this.service.getAll().get(0);
        service.deleteById(primeiro.getId());
        Optional<Mensagem> byId = service.getById(primeiro.getId());
        assertFalse(byId.isPresent());
    }

    private Mensagem getMensagem(){
        return new Mensagem(null, new Date(), "mensagem de teste", pessoa, anuncio);
    }
}