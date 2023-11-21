package lk.ijse.sellingLk.util;

import java.util.regex.Pattern;

public class ValidateUtil {

    public  String nameValidate(String name){
        if(Pattern.matches("\\b([A-Z]{1}[a-z]{1,30}[- ]{0,1}|[A-Z]{1}[- \\']{1}[A-Z]{0,1}      [a-z]{1,30}[- ]{0,1}|[a-z]{1,2}[ -\\']{1}[A-Z]{1}[a-z]{1,30}){2,5}",name))return name;
        else return null;
    }

    public  String addressValidate(String address){
        if( Pattern.matches("^\\d+\\/[A-Za-z\\d\\s,']+",address))return address;
        else return null;
    }

    public String emailValidate(String email){
        if (Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b",email))return email;
        else return null;
    }
}
