package com.kdmeubichinho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kdmeubichinho.entities.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer>{

}