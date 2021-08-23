package com.kdmeubichinho.services;

import com.kdmeubichinho.entities.Pessoa;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JwtServiceTest {

    private JwtService service;
    private static final String TOKEN_EXPIRADO = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZXJhclRva2VuIiwiZXhwIjoxNjI3NTQ2NjQ1fQ.zRrgyDHdOUguRiSQKRSE2EmcOGcZXnJuyeMEmfHQt33_bx2QOjt09MBeXnQzvY8Qhynalrok9OaPjiZx7rHn5Q";

    @Autowired
    public JwtServiceTest(JwtService service){
        this.service = service;
    }

    @Test
    @Order(1)
    void gerarToken() {
        Pessoa build = Pessoa.builder()
                .nome("gerarToken")
                .email("gerarToken")
                .build();

        String token = this.service.gerarToken(build);
        assertNotNull(token);
    }

    @Test
    void tokenValido() {
        Pessoa build = Pessoa.builder()
                .nome("tokenValido")
                .email("tokenValido")
                .build();

        String token = this.service.gerarToken(build);
        assertTrue(this.service.tokenValido(token));
    }

    @Test
    void tokenInvalido() {
        assertFalse(this.service.tokenValido("tokenInvalido"));
    }

    @Test
    void tokenExpirado() {
        assertFalse(this.service.tokenValido(TOKEN_EXPIRADO));
    }

    @Test
    void obterEmailUsuario() {
        Pessoa build = Pessoa.builder()
                .nome("obterEmailUsuario")
                .email("obterEmailUsuario")
                .build();

        String token = this.service.gerarToken(build);

        String email = this.service.obterEmailUsuario(token);

        assertEquals("obterEmailUsuario", email);
    }
}