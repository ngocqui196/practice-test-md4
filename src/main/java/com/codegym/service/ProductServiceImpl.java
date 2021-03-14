package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
     private IProductRepository repositoryProduct;

    @Override
    public List<Product> findAll() {
        return repositoryProduct.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repositoryProduct.findOne(id);
    }

    @Override
    public void update(Product product) {
        repositoryProduct.save(product);
    }



    @Override
    public void save(Product product) {
        repositoryProduct.save(product);
    }

    @Override
    public void remove(Long id) {
        repositoryProduct.delete(id);
    }

    @Override
    public List<Product> findTopByPriceIsGreaterThan() {
        return repositoryProduct.findTopByPriceIsGreaterThan();
    }

    @Override
    public List<Product> findTopByLastest(){
        return repositoryProduct.findTopByLastest();
    }

    @Override
    public List<Product> findAllProductByCategory(Category category) {
        return repositoryProduct.findAllByCategory(category);
    }
}
