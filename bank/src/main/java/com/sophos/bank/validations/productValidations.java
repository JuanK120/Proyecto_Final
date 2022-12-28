package com.sophos.bank.validations;



import com.sophos.bank.entity.product;
import com.sophos.bank.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

public class productValidations {

    public boolean isBalancePositive(int balance){
        if (balance > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean noExemptAccounts(List<product> productsOfClient){
        if (!productsOfClient.isEmpty()){
            return false;
        }else{
            return true;
        }

    }

    public String randomString(int stringType){
        String returnString="";
        if (stringType == 1){
            returnString = "46";
        } else if (stringType == 2){
            returnString = "23";
        }
        int rest= (int)(Math.random()*100000000);
        returnString = returnString+rest;
        return returnString;
    }

    public int gmfValue(int balance, boolean exempt){
        if (exempt){
            return balance;
        }else{
            return (int)Math.floor(balance*0.996);
        }
    }

}
