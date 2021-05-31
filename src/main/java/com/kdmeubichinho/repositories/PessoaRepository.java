package com.kdmeubichinho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdmeubichinho.entities.Pessoa;



public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	Pessoa findOneByEmail(String email);
    Optional<Pessoa> findByEmail(String email);

}
