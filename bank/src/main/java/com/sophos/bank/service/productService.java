package com.sophos.bank.service;

import com.sophos.bank.entity.product;

import java.util.List;
import java.util.Optional;

public interface productService {
    public product createProduct(product product);
    public List<product> getAllProducts();
    public Optional<product> getProductById(int productId);
    public boolean deleteProductById(int productId);

}
