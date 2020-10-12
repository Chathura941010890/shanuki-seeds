package com.openuniversity.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openuniversity.springjwt.models.ERole;
import com.openuniversity.springjwt.models.Role;
import com.openuniversity.springjwt.models.User;
import com.openuniversity.springjwt.payload.request.LoginRequest;
import com.openuniversity.springjwt.payload.request.SignupRequest;
import com.openuniversity.springjwt.payload.response.JwtResponse;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.RoleRepository;
import com.openuniversity.springjwt.repository.UserRepository;
import com.openuniversity.springjwt.security.jwt.JwtUtils;
import com.openuniversity.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	EmailValidation emailValidation;

	@Autowired
	PasswordValidation passwordValidation;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		boolean usernameExists = false;
		boolean password = false;
		User loginUser;
		if (userRepository.existsByUsername(loginRequest.getUsername())) {
			usernameExists = true;
			loginUser = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
		} else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: The Username has not been registered!"));
		}

		if (encoder.matches(loginRequest.getPassword(), loginUser.getPassword())) {
			password = true;
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Invalid password!"));
		}

		if (usernameExists && password) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles));
		}
		else{
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username or Password is incorrect!"));
		}

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println(signUpRequest.getUsername());
		boolean validEmail = false;
		boolean validPassword = false;

		if (emailValidation.validateEmail(signUpRequest.getEmail())) {
			validEmail = true;
		}
		else{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: enter a valid email"));
		}

		if (passwordValidation.validatePassword(signUpRequest.getPassword())) {
			validPassword = true;
		}
		else{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: enter a valid password"));
		}

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role applicantRole = roleRepository.findByName(ERole.ROLE_GUEST)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(applicantRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "Admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "Operator":
						Role operatorRole = roleRepository.findByName(ERole.ROLE_OPERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(operatorRole);

						break;

//					case "superadmin":
//						Role superAdmin = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(superAdmin);
//
//						break;
//
//					case "admin":
//						Role admin = roleRepository.findByName(ERole.ROLE_ADMIN)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(admin);
//
//						break;

					default:
						Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST )
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(guestRole);
				}
			});
		}
		if(validEmail && validPassword) {
			user.setRoles(roles);
			userRepository.save(user);
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


}
