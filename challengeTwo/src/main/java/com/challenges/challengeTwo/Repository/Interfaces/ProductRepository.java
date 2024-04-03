package com.challenges.challengeTwo.Repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenges.challengeTwo.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    
}
