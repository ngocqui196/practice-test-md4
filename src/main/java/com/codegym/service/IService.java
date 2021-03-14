package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IService<T> {

//    Page<Customer> findAllByFirstNameContaining(Pageable pageable);

    List<T> findAll();


    T findById(Long id);
    void update(T t);

    void save(T t);
    void remove(Long id);
}
