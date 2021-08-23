package com.kdmeubichinho.repositories;

import com.kdmeubichinho.entities.Especie;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EspecieRepository extends PagingAndSortingRepository<Especie, Integer> {

}