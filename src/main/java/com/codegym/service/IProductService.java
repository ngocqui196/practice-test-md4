package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService extends IService<Product>{
    List<Product> findTopByPriceIsGreaterThan();
    List<Product> findTopByLastest();
    List<Product> findAllProductByCategory(Category category);
}
