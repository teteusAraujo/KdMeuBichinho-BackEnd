package com.kdmeubichinho.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.kdmeubichinho.dto.CredenciaisDTO;
import com.kdmeubichinho.dto.PessoaDTO;
import com.kdmeubichinho.dto.TokenDTO;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.repositories.PessoaRepository;



public class PessoaService implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtService jwtService;
    
    @Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Iterable<Pessoa> getAllPersons(){
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> getPersonById(Integer id){
		return pessoaRepository.findById(id);
	}
	
	public Pessoa getPersonByEmail(String email) {
		return pessoaRepository.findOneByEmail(email);
	}
	
	@Transactional
    public Pessoa savePerson(PessoaDTO pessoa){
        String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
        pessoa.setSenha(senhaCriptografada);
        return pessoaRepository.save(pessoa.build());
    }
	
	public ResponseEntity<TokenDTO> authenticatePerson(CredenciaisDTO credenciais){
        try{
        	System.out.println(credenciais);
            Pessoa pessoa = Pessoa.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            		authenticate(pessoa);
            String token = jwtService.gerarToken(pessoa);
            TokenDTO tokenDto =  new TokenDTO(pessoa.getEmail(), token);
            return new ResponseEntity<TokenDTO>(tokenDto, HttpStatus.OK);
            
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }	
}
