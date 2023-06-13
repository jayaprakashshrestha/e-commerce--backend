package com.ecommerce.project.Repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.project.Models.Product;

public interface ProductRepository extends MongoRepository<Product,String> {
    
}
