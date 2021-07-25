package com.kdmeubichinho.services.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/***
 * T stands for TYPE to be returned
 * I stands for Interface to be returned
 * @param <T>
 * @param <I>
 */
public interface RestBasicService<T, I> {

    List<T> getAll();

    Page<T> getAll(Pageable page);

    Optional<T> getById(Integer id);

    I save(I I);

    void deleteById(Integer id);
}
