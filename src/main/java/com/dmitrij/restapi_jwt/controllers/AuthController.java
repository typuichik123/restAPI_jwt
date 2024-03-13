package com.dmitrij.restapi_jwt.controllers;

import com.dmitrij.restapi_jwt.DTOs.JwtRequest;
import com.dmitrij.restapi_jwt.DTOs.JwtResponse;
import com.dmitrij.restapi_jwt.DTOs.RegistrationUserDTO;
import com.dmitrij.restapi_jwt.DTOs.UserDTO;
import com.dmitrij.restapi_jwt.entities.User;
import com.dmitrij.restapi_jwt.exeptions.ApplicationError;
import com.dmitrij.restapi_jwt.service.UserService;
import com.dmitrij.restapi_jwt.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(),
                    "wrong name or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        if (!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(),
                    "passwords are not equals"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUserName(registrationUserDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(),
                    "user already exists"), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registrationUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        switch (registrationUserDTO.getRole()) {
            case "POSTS" -> userService.createNewUserForPosts(user);
            case "USERS" -> userService.createNewUserForUsers(user);
            case "ALBUMS" -> userService.createNewUserForAlbums(user);
            default -> {
                return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(),
                        "wrong role in registration"), HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok(new UserDTO(user.getUserName(),  registrationUserDTO.getPassword()));
    }
}


