package com.sophos.bank.service;

import com.sophos.bank.entity.identificationType;
import com.sophos.bank.repository.identificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class identificationTypeServiceImplementation implements identificationTypeService{

    @Autowired
    identificationTypeRepository identificationTypeRepository;

    @Override
    public identificationType createIdentificationType(identificationType identificationType) {
        return identificationTypeRepository.save(identificationType);
    }

    @Override
    public List<identificationType> getAllIdentificationTypes() {
        return identificationTypeRepository.findAll();
    }

    @Override
    public Optional<identificationType> getIdentificationTypeById(int id) {
        return identificationTypeRepository.findById(id);
    }

    @Override
    public boolean deleteIdentificationTypeById(int id) {
        return getIdentificationTypeById(id).map(identificationType -> {
            identificationTypeRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
