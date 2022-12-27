package com.sophos.bank.service;

import com.sophos.bank.entity.movementType;
import com.sophos.bank.repository.movementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class movementTypeServiceImplementation implements movementTypeService{

    @Autowired
    movementTypeRepository movementTypeRepository;

    @Override
    public movementType createMovementType(movementType movementType) {
        return movementTypeRepository.save(movementType);
    }

    @Override
    public List<movementType> getAllMovementTypes() {
        return movementTypeRepository.findAll();
    }

    @Override
    public Optional<movementType> getMovementTypeById(int id) {
        return movementTypeRepository.findById(id);
    }

    @Override
    public boolean deleteMovementTypeById(int id) {
        return getMovementTypeById(id).map(movementType -> {
            movementTypeRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
