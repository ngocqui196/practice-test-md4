package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products order by price desc limit 5", nativeQuery = true)
    List<Product> findTopByPriceIsGreaterThan();
    @Query(value = "select * from products order by date desc limit 5",nativeQuery = true)
    List<Product> findTopByLastest();
    List<Product> findAllByCategory(Category category);
}
