package com.openuniversity.springjwt.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class EmailValidation {

    private boolean valid = true;
    private int atSignIndex = 0;
    private int dotSingIndex = 0;
    private int emailLength = 0;
    private char[] specialChars = {'!', '#', '$', '%' ,'&', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|'};
    private char[] upperLetters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private char[] lowerLetters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char[] all = {'!', '#', '$', '%' ,'&', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9'};
    @PostMapping("/validate-email")
    public boolean validateEmail(@Valid @RequestBody String emailAddress){
        boolean recipientNameValidation = false;
        boolean domainValidation = true;
        boolean topDomainValidation = false;
        boolean signIndex = false;

        //get the length of email address
        emailLength = emailAddress.length();

        //find the indexes of @ and .
        for(int i = 0; i < emailLength; i++){
            char symbol = emailAddress.charAt(i);
            if(symbol == '@'){
                atSignIndex = i;
            }
            else if(symbol == '.'){
                dotSingIndex = i;
            }
        }

        //validation of @ and .
        if(atSignIndex != 0 && dotSingIndex != 0){
            signIndex = true;
        }
        else {
            signIndex = false;
        }

        //validation of Recipient name
        String recipientName = emailAddress.substring(0, atSignIndex);
        int lengthRecipientName = recipientName.length();
        boolean content = false;
        boolean length = true;
        boolean firstChar = true;
        boolean lastChar = true;
        boolean doubleChar = true;

        for (int i = 0; i < lengthRecipientName; i++) {
            content = false;
            for (int j = 0; j < all.length; j++) {
                if (recipientName.charAt(i) == all[j]) {
                    content = true;
                }
            }
            if(content == false) {
                break;
            }
        }

        if (lengthRecipientName > 64) {
            length = false;
        }

        for (int i = 0; i < specialChars.length; i++) {
            if (recipientName.charAt(0) == specialChars[i]) {
                firstChar = false;
            }
            if (recipientName.charAt(lengthRecipientName - 1) == specialChars[i]) {
                lastChar = false;
            }
        }

        for (int i = 0; i < lengthRecipientName - 1; i++) {
            for (int j = 0; j < specialChars.length; j++) {
                if (recipientName.charAt(i) == specialChars[j] && recipientName.charAt(i + 1) == specialChars[j]) {
                    doubleChar = false;
                }
            }
        }

        if(content == true && length == true && firstChar == true && lastChar == true && doubleChar == true){
            recipientNameValidation = true;
        }
        else{
            recipientNameValidation = false;
        }

        //validation of domain name
        String domainName = emailAddress.substring(atSignIndex + 1, dotSingIndex);
        int lengthDomainName = domainName.length();
        boolean lenDom = true;
        boolean conDom = false;

        char[] allForDomain = {'-', '.',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        if (lengthDomainName <= 253) {
            lenDom = true;
        }

        for (int i = 0; i < lengthDomainName; i++) {
            conDom = false;
            for (int j = 0; j < allForDomain.length; j++) {
                if (domainName.charAt(i) == allForDomain[j]) {
                    conDom = true;
                }
            }
            if(conDom == false) {
                break;
            }
        }

        if(lenDom == true && conDom == true){
            domainValidation = true;
        }
        else {
            domainValidation = false;
        }

        //validation of top level domain
        String topLevelDomain = emailAddress.substring(dotSingIndex + 1, emailLength);
        int lengthTopLevelDomain = topLevelDomain.length();

        for (int i = 0; i < lengthTopLevelDomain; i++) {
            topDomainValidation = false;
            for (int j = 0; j < lowerLetters.length; j++) {
                if (topLevelDomain.charAt(i) == lowerLetters[j]) {
                    topDomainValidation = true;
                }
            }
            if(topDomainValidation == false) {
                break;
            }
        }

        //Combine validations
        if(recipientNameValidation == true && domainValidation == true && topDomainValidation == true && signIndex == true){
            valid = true;
        }
        else{
            valid = false;
        }
        return valid;
    }
}
