package com.kdmeubichinho.repositories;

import com.kdmeubichinho.entities.Mensagem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MensagemRepository extends PagingAndSortingRepository<Mensagem, Integer> {

}