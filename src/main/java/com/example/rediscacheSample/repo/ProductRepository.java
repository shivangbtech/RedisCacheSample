package com.example.rediscacheSample.repo;

import com.example.rediscacheSample.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
