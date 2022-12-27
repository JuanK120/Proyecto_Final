package com.sophos.bank.service;

import com.sophos.bank.entity.movementType;

import java.util.List;
import java.util.Optional;

public interface movementTypeService {
    public movementType createMovementType(movementType movementType);
    public List<movementType> getAllMovementTypes();
    public Optional<movementType> getMovementTypeById(int id);
    public boolean deleteMovementTypeById(int id);
}
