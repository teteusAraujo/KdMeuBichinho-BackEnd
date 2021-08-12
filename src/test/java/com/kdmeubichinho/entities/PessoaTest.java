package com.kdmeubichinho.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaTest {

    Pessoa p;

    @BeforeAll
    public void setup(){
        this.p = new Pessoa(
                1, "nome", "email", "cep", "logradouro", "complemento", "bairro",
                "localidade", "uf", "ibge", "ddd", "numeroResidencial", "celular",
                "senha", true);
    }

    @Test
    void noArgsConstructor(){
        Pessoa pessoa = new Pessoa();
        assertNotNull(pessoa);
    }

    @Test
    void setters() {
        Pessoa pessoa = new Pessoa()
                .setIdPessoa(1)
                .setNome("teste-nome")
                .setEmail("")
                .setCep("00000000")
                .setLogradouro("")
                .setComplemento("")
                .setBairro("")
                .setLocalidade("")
                .setUf("")
                .setIbge("")
                .setDdd("")
                .setNumeroResidencial("")
                .setCelular("")
                .setSenha("")
                .setAdmin(true);
        assertEquals("teste-nome", pessoa.getNome());
    }

    @Test
    void testConstructorAndGetter() {
        assertEquals(1, p.getIdPessoa());
        assertEquals("email",p.getEmail());
        assertEquals("email",p.getUsername());
        assertEquals("cep",p.getCep());
        assertEquals("logradouro",p.getLogradouro());
        assertEquals("complemento",p.getComplemento());
        assertEquals("bairro",p.getBairro());
        assertEquals("localidade",p.getLocalidade());
        assertEquals("uf",p.getUf());
        assertEquals("ibge",p.getIbge());
        assertEquals("ddd",p.getDdd());
        assertEquals("numeroResidencial",p.getNumeroResidencial());
        assertEquals("celular",p.getCelular());
        assertEquals("senha",p.getSenha());
        assertEquals("senha",p.getPassword());
    }

    @Test
    void testMinimalConstructorAndGetter(){
        Pessoa pessoa = new Pessoa("nome2", "email2", "cep2", "celular2", "senha2");
        assertEquals("nome2",pessoa.getNome());
        assertEquals("email2",pessoa.getEmail());
        assertEquals("cep2",pessoa.getCep());
        assertEquals("celular2",pessoa.getCelular());
        assertEquals("senha2",pessoa.getSenha());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(p.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(p.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(p.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(p.isEnabled());
    }

    @Test
    void builder() {
        Pessoa pessoa = Pessoa.builder()
                .idPessoa(1)
                .nome("teste-nome")
                .email("")
                .cep("00000000")
                .logradouro("")
                .complemento("")
                .bairro("")
                .localidade("")
                .uf("")
                .ibge("")
                .ddd("")
                .numeroResidencial("")
                .celular("")
                .senha("")
                .admin(true)
                .build();
        assertEquals("teste-nome", pessoa.getNome());
    }

    @Test
    void isAdmin() {
        assertTrue(p.isAdmin());
    }

    @Test
    void getAuthority(){
        Collection<? extends GrantedAuthority> authorities = p.getAuthorities();
        assertEquals(0, authorities.size());
    }
}