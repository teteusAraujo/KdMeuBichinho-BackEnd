package com.kdmeubichinho.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;


public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>, JpaSpecificationExecutor<Anuncio>{
	
	Page<Anuncio> findByIdAnimal_Sexo(AnimalSexo sexo, Pageable pageable);
	Page<Anuncio> findByIdAnimal_Especie_IdEspecie(Integer id, Pageable pageable);
	Page<Anuncio> findByIdCategoria_IdCategoria(Integer id, Pageable pageable);
	Page<Anuncio> findByIdAnimal_ClassificacaoEtaria(AnimalClassificacaoEtaria classificacaoEtaria, Pageable pageable);
	Page<Anuncio> findByIdAnimal_Porte(AnimalPorte porte, Pageable pageable);
	List<Anuncio> findByIdAnimal_SexoAndIdAnimal_Porte(AnimalSexo sexo, AnimalPorte porte);
	Page<Anuncio> findByidPessoa_Email(String email, Pageable pageable);

}
