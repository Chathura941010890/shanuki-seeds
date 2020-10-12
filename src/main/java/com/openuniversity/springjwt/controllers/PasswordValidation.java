package com.openuniversity.springjwt.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class PasswordValidation {

    private boolean valid = true;
    private int pwLength = 0;
    private char[] specialChars = {'@', '(', ')', '[', ']', ':', ';', '!', '<', '>', ',', '.', '#', '$', '%' ,'&', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{','}', '|'};
    private char[] all = {'@', '(', ')', '[', ']', ':', ';', '!', '<', '>', ',', '.', '#', '$', '%' ,'&', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{','}', '|',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9'};
    @PostMapping("/validate-password")
    public boolean validatePassword(@Valid @RequestBody String password){
        //get the length of password
        pwLength = password.length();

        //validation of password
        boolean content = false;
        boolean length = true;
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean number = false;
        boolean symbol = false;

        for (int i = 0; i < pwLength; i++) {
            content = false;
            for (int j = 0; j < all.length; j++) {
                if (password.charAt(i) == all[j]) {
                    content = true;
                }
            }
            if(content == false) {
                break;
            }
        }

        if (pwLength > 40 || pwLength < 6) {
            length = false;
        }

        for (int i = 0; i < pwLength; i++) {
            char x = password.charAt(i);
            if (Character.isUpperCase(x)) {
                upperCase = true;
            }
            else if (Character.isLowerCase(x)) {
                lowerCase = true;
            }
            else if (Character.isDigit(x)) {
                number = true;
            }
            else {
                for (int j = 0; j < specialChars.length; j++) {
                    if (x == specialChars[j]) {
                        symbol = true;
                        break;
                    }
                }
            }
        }

        //Combine validations
        if(content == true && length == true && upperCase == true && lowerCase == true && number == true || symbol == true){
            valid = true;
        }
        else{
            valid = false;
        }
        return valid;
    }
}
