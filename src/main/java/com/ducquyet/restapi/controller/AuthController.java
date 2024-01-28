package com.ducquyet.restapi.controller;

import com.ducquyet.restapi.dto.AuthenticationRequest;
import com.ducquyet.restapi.dto.AuthenticationResponse;
import com.ducquyet.restapi.dto.RegisterRequest;
import com.ducquyet.restapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        System.out.println("this is request"+request);
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }
    @PostMapping("/register")
    void register(@RequestBody @Valid RegisterRequest request) {
        authenticationService.register(request);
    }
}
