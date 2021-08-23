package com.kdmeubichinho.services;

import com.kdmeubichinho.dto.CredenciaisDTO;
import com.kdmeubichinho.dto.PessoaDTO;
import com.kdmeubichinho.dto.TokenDTO;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.repositories.PessoaRepository;
import com.kdmeubichinho.services.generics.RestBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements RestBasicService<Pessoa, PessoaDTO> {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PessoaRepository pessoaRepository;

<<<<<<< HEAD

public class PessoaService implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtService jwtService;
    
=======
>>>>>>> 838de23df24748ee8c02c17c0590a453f46a350c
    @Autowired
    public PessoaService(PasswordEncoder passwordEncoder, JwtService jwtService,
                         PessoaRepository pessoaRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Page<Pessoa> getAll(Pageable page) {
        return pessoaRepository.findAll(page);
    }

    @Override
    public Optional<Pessoa> getById(Integer id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public PessoaDTO save(PessoaDTO i) {
        this.pessoaRepository.save(i.build());
        return i;
    }

    public Pessoa save(Pessoa p) {
        return this.pessoaRepository.save(p);
    }

    @Override
    public void deleteById(Integer id) {
        this.pessoaRepository.deleteById(id);
    }

    public Optional<Pessoa> getPersonByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    @Transactional
    public Pessoa savePerson(PessoaDTO pessoa) {
        String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
        pessoa.setSenha(senhaCriptografada);
        return pessoaRepository.save(pessoa.build());
    }

    public ResponseEntity<TokenDTO> authenticatePerson(CredenciaisDTO credenciais) {
        try {
            Pessoa pessoa = Pessoa.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            Pessoa p = getPersonByEmail(pessoa.getEmail()).orElseThrow(() -> new UsernameNotFoundException(""));
            String token = jwtService.gerarToken(p);
            TokenDTO tokenDto = new TokenDTO(p.getEmail(), token);
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);

        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
