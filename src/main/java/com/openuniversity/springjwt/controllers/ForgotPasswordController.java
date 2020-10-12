package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.ConfirmationToken;
import com.openuniversity.springjwt.models.User;
import com.openuniversity.springjwt.payload.request.ForgotPasswordRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.ConfirmationTokenRepository;
import com.openuniversity.springjwt.repository.UserRepository;

import com.openuniversity.springjwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.MultiValueMap;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailValidation emailValidation;

    @Autowired
    EmailController emailController;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> restPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        boolean emailExist = true;
        boolean isNull = false;

        if(forgotPasswordRequest.getEmail().equals(null)){
            isNull = true;
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Please enter your email address!"));
        }


        if (userRepository.existsByEmail(forgotPasswordRequest.getEmail())) {
            emailExist = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The email address has not been registered!"));
        }
        if (emailExist && isNull==false) {

            User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            String link = "http://localhost:4200/reset?token=" + confirmationToken.getConfirmationToken();

            MultiValueMap<String, String > mMap = new LinkedMultiValueMap<>();
            mMap.add("emailTo",forgotPasswordRequest.getEmail());
            mMap.add("emailFrom", "chathurajayawardane@gmail.com");
            mMap.add("emailSubject","Password rest link.");
            mMap.add("emailContent",link);

            emailController.sendmail(mMap);


            return ResponseEntity.ok().body(new MessageResponse("Success: The reset link has successfully been sent to your email!"));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Error: The email address has not been registered!"));
        }
    }
}
