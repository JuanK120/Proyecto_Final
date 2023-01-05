package com.sophos.bank.service;

import com.sophos.bank.entity.client;
import com.sophos.bank.entity.product;
import com.sophos.bank.repository.productRepository;
import jakarta.transaction.Transactional;
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
        if (product.getCreationUser().equals(product.getModificationUser())){
            product.setModificationUser(product.getCreationUser());
        }
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

    @Override
    @Transactional
    public product updateProduct(product product) {
         productRepository.setProductInfoById(
                product.getProductId(),product.getOwner(),
                 product.getProductType(),product.getProductNumber(),
                 product.getState(),product.getBalance(),
                 product.getAvailableBalance(),product.isGmfExempt(),
                 product.getModificationDate(),product.getModificationUser()
        );
        return product;
    }

    @Override
    public List<product> getProductByProductNumber(long productNumber) {
        return productRepository.findAllByProductNumber(productNumber);
    }

    @Override
    public List<product> getProductByOwnerAndGmfexempt(client client, boolean exempt) {
        return productRepository.findByOwnerAndGmfExempt(client,exempt);
    }

    @Override
    public List<product> findAllByOwner(Optional<client> clientById) {
        return productRepository.findAllByOwner(clientById);
    }
}
