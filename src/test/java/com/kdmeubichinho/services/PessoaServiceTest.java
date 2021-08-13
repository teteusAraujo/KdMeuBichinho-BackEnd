package com.kdmeubichinho.services;

import com.kdmeubichinho.dto.CredenciaisDTO;
import com.kdmeubichinho.dto.PessoaDTO;
import com.kdmeubichinho.dto.TokenDTO;
import com.kdmeubichinho.entities.Pessoa;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaServiceTest {

    private PessoaService service;

    @Autowired
    public PessoaServiceTest(PessoaService service) {
        this.service = service;
    }

    @BeforeAll
    public void setup() {
        String nome = "nome-%s";
        String email = "email-%s";

        for (int i = 0; i < 60; i++) {
            Pessoa p = new Pessoa(String.format(nome, new DecimalFormat("00").format(i)), String.format(email, new DecimalFormat("00").format(i)), "cep", "celular", "senha");
            this.service.save(p);
        }
    }

    @Test
    @Order(1)
    void getAllPaged() {
        Page<Pessoa> all = this.service.getAll(PageRequest.of(0, 50));
        assertEquals(50, all.stream().count());
    }

    @Test
    @Order(2)
    void getAll() {
        List<Pessoa> all = this.service.getAll();
        assertTrue(all.size() > 60);
    }

    @Test
    @Order(3)
    void getById() {
        Optional<Pessoa> byId = this.service.getById(1);
        assertTrue(byId.isPresent());
    }

    @Test
    void save() {
        Pessoa p = new Pessoa("nome-test-save", "nome-test-save", "cep", "celular", "senha");
        Pessoa save = this.service.save(p);
        assertEquals("nome-test-save", save.getNome());
    }

    @Test
    void testSave() {
        PessoaDTO pessoaDTO = getPessoaDTO();

        PessoaDTO save = this.service.save(pessoaDTO);
        assertEquals("nome-teste-dto", save.getNome());
    }

    @Test
    void deleteById() {
        List<Pessoa> all = this.service.getAll();
        this.service.deleteById(all.get(all.size() - 1).getIdPessoa());
        Optional<Pessoa> byId = this.service.getById(all.get(all.size() - 1).getIdPessoa());
        assertFalse(byId.isPresent());
    }

    @Test
    void getPersonByEmail() {
        PessoaDTO pessoaDTO = getPessoaDTO();
        pessoaDTO.setEmail("getPersonByEmail");

        PessoaDTO save = this.service.save(pessoaDTO);
        assertEquals("getPersonByEmail", save.getEmail());
    }

    @Test
    void savePerson() {
        PessoaDTO pessoaDTO = getPessoaDTO();
        pessoaDTO.setEmail("savePerson");

        final Pessoa save = this.service.savePerson(pessoaDTO);
        assertEquals("savePerson", save.getEmail());
    }

    @Test
    void authenticatePerson() {
        PessoaDTO pessoaDTO = getPessoaDTO();
        pessoaDTO.setNome("authenticatePerson");
        pessoaDTO.setEmail("authenticatePerson");
        PessoaDTO save = this.service.save(pessoaDTO);
        CredenciaisDTO credenciaisDTO = new CredenciaisDTO(save.getEmail(), save.getSenha());

        ResponseEntity<TokenDTO> entity = this.service.authenticatePerson(credenciaisDTO);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void authenticatePersonNaoExistente() {
        CredenciaisDTO credenciaisDTO = new CredenciaisDTO("authenticatePersonNaoExistente", "authenticatePersonNaoExistente");
        assertThrows(ResponseStatusException.class, () -> this.service.authenticatePerson(credenciaisDTO));
    }

    private PessoaDTO getPessoaDTO() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setNome("nome-teste-dto");
        pessoaDTO.setEmail("email-teste-dto");
        pessoaDTO.setCep("00000-000");
        pessoaDTO.setCelular("000 000 000");
        pessoaDTO.setSenha("teste-senha");

        return pessoaDTO;
    }
}