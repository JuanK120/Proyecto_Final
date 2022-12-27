package com.sophos.bank.service;

import com.sophos.bank.entity.product;
import com.sophos.bank.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productServiceImplementation implements productService{

    @Autowired
    productRepository productRepository;

    @Override
    public product createProduct(product product) {
        return productRepository.save(product);
    }

    @Override
    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean deleteProductById(int id) {
        return getProductById(id).map(product -> {
            productRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
