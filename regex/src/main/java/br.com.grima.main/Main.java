package br.com.grima.main;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
         Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        //Pattern.compile("^(([^<>!()[\\]\\\\.,;:\\s@']+(\\.[^!<>()[\\]\\\\.,;:\\s@']+)*)|('.+'))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    public static void main(String[] args) {

        for(String email : getEmailList()){
            //System.out.println("Validando Email [" + email + "] Resultado [" + (validateDomain(email)) + "]");
            System.out.println("Validando Email [" + email + "] Resultado [" + (validate(email) && validateDomain(email) && validateBURule(email)) + "]");
        }

    }

    private static boolean validateDomain(String email){
        boolean isValid = false;
        if(!StringUtils.isEmpty(email)){
            String check = email.substring(email.indexOf("@"));

            if(check.contains("gmail")){
                 if(check.endsWith(".com")) return true;
                 if(check.endsWith(".br")) return false;
            }

            if(check.contains("uol")){
                if(check.endsWith(".com")) return false;
                if(check.endsWith(".com.br")) return true;
            }

        }
        return isValid;
    }

    private static boolean validateBURule(String email){

        if(!StringUtils.isEmpty(email)){
            String check = email.toUpperCase();

            if(check.contains("NAOTEM")) return false;
            if(check.contains("TEMNAO")) return false;
            if(check.contains("NAOEXISTE")) return false;
            if(check.contains("NAOPOSSUI")) return false;

        }
        return true;
    }



    /***
     * Lista de email para serem validados.
     * @return Lista de emails.
     */
    private static List<String> getEmailList(){
        List<String> lista = new ArrayList<>();
        lista.add("meuemail@gmail.com");
        lista.add("meuemail@gmail.com.");
        lista.add("meuemail@gmail.com.br");
        lista.add("meu.email.@gmail.com.br.");
        lista.add("meu#email@gmail.com.br");
        lista.add("meu!email@gmail.com.br");
        lista.add("meu$email@gmail.com.br");
        lista.add("meu.email@gmail.com.br");
        lista.add("[meu.email@gmail.com.br");
        lista.add("<meu.email.br@gmail.com.br");
        lista.add("naotem@naotem.com.br");
        lista.add("naoexiste@naoexiste.com");
        lista.add("naopossui@outlook.com");

        lista.add("meu.email@uol.com");
        lista.add("meuemail@uol.com.br");


        return lista;

    }


}
