package com.github.paulocarpinetti.rest_udemy_2025.controllers;

import com.github.paulocarpinetti.rest_udemy_2025.controllers.docs.AuthControllerDocs;
import com.github.paulocarpinetti.rest_udemy_2025.data.dto.config.AccountCredentialsDTO;
import com.github.paulocarpinetti.rest_udemy_2025.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    @Autowired
    AuthService authServices;

    @PostMapping(value = "/signin")
    @Override
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.signIn(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @PutMapping(value = "/refresh/{username}")
    @Override
    public ResponseEntity<?> refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @PostMapping(
            value = "/createuser",
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public AccountCredentialsDTO create(@RequestBody AccountCredentialsDTO credentials){

        return authServices.create(credentials);
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
