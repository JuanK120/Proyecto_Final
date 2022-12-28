package com.sophos.bank.validations;




import com.sophos.bank.entity.product;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.sophos.bank.validations.auxiliars.*;

public class clientValidations {

    public clientValidations(){

    }

    public boolean isOfAge(Date birthDate){
        int age = getDiffYears(birthDate, new Date(System.currentTimeMillis()));
        return age >= 18;
    }

    public boolean isDeletable(List<product> products){
        return products.stream().noneMatch(prod -> prod.getState().getIdState()!=3);
    }

    public boolean isEmail(String email){
        return Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                .matcher(email)
                .matches();
    }

    public boolean is2CharLong(String string){
        return string.length() > 2;
    }
}

