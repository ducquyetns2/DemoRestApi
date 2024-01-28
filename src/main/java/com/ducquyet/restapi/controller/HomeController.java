package com.ducquyet.restapi.controller;

import com.ducquyet.restapi.entity.EnumRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    ResponseEntity<String> homePage() {
        return new ResponseEntity<>("This is the home page",HttpStatus.OK);
    }
    @GetMapping("/test")
    ResponseEntity<String> testPage() {
        return new ResponseEntity<>("This is the test page",HttpStatus.OK);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    String adminPage() {
        return "This is the admin page";
    }
}
