package com.challenges.challengeTwo.Repository.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenges.challengeTwo.Entity.Product;
import com.challenges.challengeTwo.Repository.Interfaces.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Product updateProduct(Long id, Product product) {
        Product product2=productRepository.findById(id).get();
		product2.setDescription(product.getDescription());
		product2.setName(product.getName());
		product2.setPrice(product.getPrice());
        return productRepository.save(product2);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
