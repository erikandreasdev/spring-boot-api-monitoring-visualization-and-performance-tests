package com.erikandreas.metricsdemo.service;

import com.erikandreas.metricsdemo.entity.Product;
import com.erikandreas.metricsdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

}
