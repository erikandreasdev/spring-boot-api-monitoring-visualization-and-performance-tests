package com.erikandreas.metricsdemo.service;

import com.erikandreas.metricsdemo.entity.Product;

import java.util.List;

public interface ProductService {

    void deleteAll();

    List<Product> findAll();

    Product save(Product product);

    
}
