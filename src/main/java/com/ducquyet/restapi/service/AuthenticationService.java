package com.ducquyet.restapi.service;

import com.ducquyet.restapi.dto.AuthenticationRequest;
import com.ducquyet.restapi.dto.AuthenticationResponse;
import com.ducquyet.restapi.dto.RegisterRequest;
import com.ducquyet.restapi.entity.Role;
import com.ducquyet.restapi.entity.User;
import com.ducquyet.restapi.repository.RoleRepository;
import com.ducquyet.restapi.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Data
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication=new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("User"+ authentication.getPrincipal());
        Optional<User> user=userRepository.findByUsername(request.getUsername());
        var token=jwtService.generateToken(user.orElseThrow());
        return new AuthenticationResponse(token);
    }
    public void register(RegisterRequest request) {
        Set<Role> roles=roleRepository.findByRoleIn(request.getRoles());
        User user=User.builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
    }
}
