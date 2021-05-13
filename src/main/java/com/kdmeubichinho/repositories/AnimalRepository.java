package com.kdmeubichinho.repositories;

import org.springframework.data.repository.CrudRepository;
import com.kdmeubichinho.entities.Animal;



public interface AnimalRepository extends CrudRepository<Animal, Integer>{
	
}