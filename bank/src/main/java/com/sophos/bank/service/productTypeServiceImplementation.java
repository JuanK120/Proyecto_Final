package com.sophos.bank.service;

import com.sophos.bank.entity.productType;
import com.sophos.bank.repository.productTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productTypeServiceImplementation implements productTypeService{
    @Autowired
    productTypeRepository productTypeRepository;

    @Override
    public productType createProductType(productType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public List<productType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Override
    public Optional<productType> getProductTypeById(int id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public boolean deleteProductType(int id) {
        return getProductTypeById(id).map(productType -> {
            productTypeRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
