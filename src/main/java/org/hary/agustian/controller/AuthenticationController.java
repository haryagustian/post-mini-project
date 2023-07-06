package org.hary.agustian.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.model.auth.AuthenticationRequest;
import org.hary.agustian.model.auth.AuthenticationResponse;
import org.hary.agustian.model.user.UserRequest;
import org.hary.agustian.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registered")
    public ResponseEntity<AuthenticationResponse> registered(
            @RequestBody UserRequest userRequest
            ){
        if (
                userRequest.getUsername().isBlank() ||
                        userRequest.getEmail().isBlank() ||
                        userRequest.getPassword().isBlank()
        )
            throw new ResourceNotFoundException("PLEASE PROVIDE a username, email, password");

        return new ResponseEntity<>(authenticationService.register(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/authenticated")
    public ResponseEntity<AuthenticationResponse> authenticated(
            @RequestBody AuthenticationRequest authenticationRequest
            ){
        log.info("AUTHENTICATION REQUEST FROM BODY JSON : {}", authenticationRequest);
        if (
                authenticationRequest.getUsername().isBlank() ||
                        authenticationRequest.getPassword().isBlank()
        )
            throw new ResourceNotFoundException("PLEASE PROVIDE a username and password");

        return new ResponseEntity<>(authenticationService.authenticate(authenticationRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
