package com.sophos.bank.validations;



import com.sophos.bank.entity.product;
import com.sophos.bank.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

public class productValidations {

    @Autowired
    productRepository productRepository;

    public boolean noExemptAccounts(int clientId){
        if (!productRepository.findByProductIdAndGmfExempt(clientId,true).isEmpty()){
            return false;
        }else{
            return true;
        }

    }

}
