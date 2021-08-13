package com.kdmeubichinho.controllers.generics;

import com.kdmeubichinho.dto.generics.ObjectDTO;
import com.kdmeubichinho.entities.generics.BaseEntity;
import com.kdmeubichinho.enums.EnumException;
import com.kdmeubichinho.exception.ValidationException;
import com.kdmeubichinho.services.generics.RestBasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class RestBasicController<T extends BaseEntity, I extends ObjectDTO> {

    final RestBasicService<T, I> basicService;

    public RestBasicController(RestBasicService<T, I> basicService) {
        this.basicService = basicService;
    }

    /**
     * @param page responsible for changing the default paging behaviour
     * @return pageable list of results
     */
    @GetMapping(value = {"full"})
    public Page<T> getAllPaged(@PageableDefault(size = 50) Pageable page) {
        return this.basicService.getAll(page);
    }

    /**
     * @return Return a list of all DTOs
     */
    @GetMapping(value = {"", "all"})
    public List<T> getAll() {
        return this.basicService.getAll();
    }

    @GetMapping(value = "{id}")
    public Optional<T> getById(@PathVariable("id") Integer id) {
        return basicService.getById(id);
    }

    /**
     * create a resource
     *
     * @param i -> generic type to be persisted
     * @return Response entity containing the resource location and the resource it self
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<I> save(@RequestBody I i) {
        I saved = basicService.save(i);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(saved.build().getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    /**
     * delete a resource searching by its ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(@PathVariable Integer id) {
        T t = basicService.getById(id)
                .orElseThrow(() -> new ValidationException(EnumException.ITEM_NAO_ENCONTRADO));
        basicService.deleteById(t.getId());
        return ResponseEntity.noContent().build();
    }
}
