package com.kdmeubichinho.dto;

import com.kdmeubichinho.entities.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaDTOTest {

    @Test
    void testAlLArgsConstructorAndGetter(){
        PessoaDTO p = new PessoaDTO(1, "nome", "email", "cep", "logradouro",
                "complemento", "bairro", "localidade", "uf",
                "ibge", "ddd", "numeroResidencial", "celular", "senha");

        assertEquals(1,p.getIdPessoa());
        assertEquals("nome",p.getNome());
        assertEquals("email",p.getEmail());
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
    }

    @Test
    void testNoArgsConstructor(){
        PessoaDTO pessoaDTO = new PessoaDTO();
        assertNotNull(pessoaDTO);
    }

    @Test
    void testBuild(){
        PessoaDTO p = new PessoaDTO(1, "nome", "email", "cep", "logradouro",
                "complemento", "bairro", "localidade", "uf",
                "ibge", "ddd", "numeroResidencial", "celular", "senha");
        Pessoa build = p.build();
        assertTrue(build instanceof Pessoa);
        assertEquals("numeroResidencial", build.getNumeroResidencial());
    }

    @Test
    void testSetter(){
        PessoaDTO p = new PessoaDTO();
        p.setIdPessoa(1);
        p.setNome("nome");
        p.setEmail("email");
        p.setCep("cep");
        p.setLogradouro("logradouro");
        p.setComplemento("complemento");
        p.setBairro("bairro");
        p.setLocalidade("localidade");
        p.setUf("uf");
        p.setIbge("ibge");
        p.setDdd("ddd");
        p.setNumeroResidencial("numeroResidencial");
        p.setCelular("celular");
        p.setSenha("senha");

        assertEquals(1,p.getIdPessoa());
        assertEquals("nome",p.getNome());
        assertEquals("email",p.getEmail());
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
    }
}