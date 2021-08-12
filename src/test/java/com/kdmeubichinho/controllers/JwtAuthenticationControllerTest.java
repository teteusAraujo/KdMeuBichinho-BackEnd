package com.kdmeubichinho.controllers;

import com.kdmeubichinho.dto.PessoaDTO;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.exception.ValidationException;
import com.kdmeubichinho.model.JwtRequest;
import com.kdmeubichinho.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class JwtAuthenticationControllerTest {

    private PessoaService pessoaService;
    private JwtAuthenticationController controller;

    private static final String SENHA = "teste-senha";

    @Autowired
    public JwtAuthenticationControllerTest(PessoaService pessoaService, JwtAuthenticationController controller) {
        this.pessoaService = pessoaService;
        this.controller = controller;
    }

    @Test
    void createAuthenticationToken() {
        PessoaDTO p = getPessoaDTO();
        Pessoa save = this.pessoaService.savePerson(p);
        JwtRequest jwtRequest = new JwtRequest(p.getEmail(), SENHA);

        ResponseEntity<Object> authenticationToken = this.controller.createAuthenticationToken(jwtRequest);

        assertEquals(HttpStatus.OK, authenticationToken.getStatusCode());
    }

    @Test
    void createAuthenticationTokenUserNotFound() {
        PessoaDTO p = getPessoaDTO();
        p.setEmail("createAuthenticationTokenUserNotFound");
        JwtRequest jwtRequest = new JwtRequest(p.getEmail(), SENHA);

        assertThrows(ValidationException.class, () -> this.controller.createAuthenticationToken(jwtRequest));
    }

    @Test
    void createAuthenticationTokenUserBadCredentials() {
        PessoaDTO p = getPessoaDTO();
        p.setEmail("createAuthenticationTokenUserBadCredentials");
        p.setNome("createAuthenticationTokenUserBadCredentials");
        Pessoa save = this.pessoaService.savePerson(p);
        JwtRequest jwtRequest = new JwtRequest(p.getEmail(), "senha-diferente");

        assertThrows(ValidationException.class, () -> this.controller.createAuthenticationToken(jwtRequest));
    }

    private PessoaDTO getPessoaDTO() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setNome("createAuthenticationToken");
        pessoaDTO.setEmail("email-test@teste.com");
        pessoaDTO.setCep("00000-000");
        pessoaDTO.setCelular("000 000 000");
        pessoaDTO.setSenha("teste-senha");

        return pessoaDTO;
    }

}