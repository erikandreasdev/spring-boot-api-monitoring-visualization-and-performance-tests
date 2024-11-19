package com.erikandreas.metricsdemo.repository;

import com.erikandreas.metricsdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
