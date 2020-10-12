package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.ConfirmationToken;
import com.openuniversity.springjwt.models.User;
import com.openuniversity.springjwt.payload.request.ResetPasswordRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.ConfirmationTokenRepository;
import com.openuniversity.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class RestPasswordController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PasswordValidation passwordValidation;

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<?> resetUserPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, @RequestParam("token")String confirmationToken) {
        boolean samePasswords = false;
        boolean validPassword = false;
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(passwordValidation.validatePassword(resetPasswordRequest.getPassword())){
            validPassword = true;
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Enter a valid password!"));
        }

        if(resetPasswordRequest.getPassword().equals(resetPasswordRequest.getRetypePassword())){
            samePasswords = true;
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Passwords do not match!"));
        }

        if (token != null && samePasswords) {
            User tokenUser = userRepository.findByEmail(token.getUser().getEmail());
            System.out.println(token.getUser().getEmail());
            tokenUser.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
            userRepository.save(tokenUser);

            return ResponseEntity.ok().body(new MessageResponse("Error: Password reset successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Password reset unsuccessful"));
        }
    }
}
