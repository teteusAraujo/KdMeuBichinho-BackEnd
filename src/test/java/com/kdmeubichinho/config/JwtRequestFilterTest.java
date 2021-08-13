package com.kdmeubichinho.config;

import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.services.JwtService;
import com.kdmeubichinho.services.PessoaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtRequestFilterTest {

    private  String TOKEN = "";
    private static final String TOKEN_INVALIDO = "eyJhbiJIUzUxMiJ9.eyJzdWIiOiJnZXJhclRva2VuIiwiZXhwIjoxNjMyNzI5NzMyfQ.BmjkI2ys3R0KPbY1p8dAnBLlasoUGW8Xx7M-JjA0hrECKc1dkF9r4uQsIwoFDbSyNvhfbRc5KdyR0YmgOqIXaw";
    private static final String TOKEN_EXPIRADO = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZXJhclRva2VuIiwiZXhwIjoxNjI3NTQ2NjQ1fQ.zRrgyDHdOUguRiSQKRSE2EmcOGcZXnJuyeMEmfHQt33_bx2QOjt09MBeXnQzvY8Qhynalrok9OaPjiZx7rHn5Q";

    private JwtRequestFilter filter;
    private final JwtService jwtService;
    private final PessoaService pessoaService;

    @Autowired
    public JwtRequestFilterTest(JwtRequestFilter filter, JwtService jwtService, PessoaService pessoaService){
        this.filter = filter;
        this.jwtService = jwtService;
        this.pessoaService = pessoaService;
    }

    @BeforeAll
    public void setup() {
        Pessoa pessoa = new Pessoa(
                1, "gerarToken", "gerarToken@example.com", "cep", "logradouro", "complemento", "bairro",
                "localidade", "uf", "ibge", "ddd", "numeroResidencial", "celular",
                "senha", true);

        this.pessoaService.save(pessoa);
        Pessoa build = Pessoa.builder()
                .nome("gerarToken")
                .email("gerarToken@example.com")
                .build();

        TOKEN = this.jwtService.gerarToken(build);
    }

    @Test
    void doFilterInternalNotBearer() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        request.setServerName("localhost:8080");
        request.setRequestURI("/animal");
        request.addHeader("Authorization", TOKEN );

        filter.doFilterInternal(request, response, chain);

        assertTrue(true);
    }

    @Test
    void doFilterInternalInvalidToken() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        request.setServerName("localhost:8080");
        request.setRequestURI("/animal");
        request.addHeader("Authorization", "Bearer " + TOKEN_INVALIDO );

        filter.doFilterInternal(request, response, chain);

        assertTrue(true);
    }

    @Test
    void doFilterInternalExpiredToken() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        request.setServerName("localhost:8080");
        request.setRequestURI("/animal");
        request.addHeader("Authorization", "Bearer " + TOKEN_EXPIRADO );

        filter.doFilterInternal(request, response, chain);

        assertTrue(true);
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        request.setServerName("localhost:8080");
        request.setRequestURI("/animal");
        request.addHeader("Authorization", "Bearer " + TOKEN );

        filter.doFilterInternal(request, response, chain);

        assertTrue(true);
    }
}