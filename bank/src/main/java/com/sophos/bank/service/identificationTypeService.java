package com.sophos.bank.service;

import com.sophos.bank.entity.identificationType;

import java.util.List;
import java.util.Optional;

public interface identificationTypeService {
    public identificationType createIdentificationType(identificationType identificationType);
    public List<identificationType> getAllIdentificationTypes();
    public Optional<identificationType> getIdentificationTypeById(int id);
    public boolean deleteIdentificationTypeById(int id);
}
