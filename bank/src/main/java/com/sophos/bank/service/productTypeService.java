package com.sophos.bank.service;

import com.sophos.bank.entity.productType;

import java.util.List;
import java.util.Optional;

public interface productTypeService {
    public productType createProductType(productType productType);
    public List<productType> getAllProductTypes();
    public Optional<productType> getProductTypeById(int id);
    public boolean deleteProductType(int id);
}
