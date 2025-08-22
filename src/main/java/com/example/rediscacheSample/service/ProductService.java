package com.example.rediscacheSample.service;


import com.example.rediscacheSample.domain.Product;
import com.example.rediscacheSample.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class ProductService {
//  private static final Logger log = LoggerFactory.getLogger(ProductService.class);
  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  @Cacheable(cacheNames = "productById", key = "#id")
  @Transactional(readOnly = true)
  public Product getById(Long id) {
    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    log.info("DB HIT: loading product {} from DB", id);
    return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
  }

  @Transactional
  public Product create(Product product) {
    return repository.save(product);
  }

  @CachePut(cacheNames = "productById", key = "#id")
  @Transactional
  public Product update(Long id, Product update) {
    Product p = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    p.setName(update.getName());
    p.setPrice(update.getPrice());
    p.setCategory(update.getCategory());
    return repository.save(p);
  }

  @CacheEvict(cacheNames = "productById", key = "#id")
  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @CacheEvict(cacheNames = "productById", allEntries = true)
  public void evictAll() {}
}
