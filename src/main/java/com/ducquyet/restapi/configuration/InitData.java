package com.ducquyet.restapi.configuration;

import com.ducquyet.restapi.entity.EnumRole;
import com.ducquyet.restapi.entity.Role;
import com.ducquyet.restapi.entity.User;
import com.ducquyet.restapi.repository.RoleRepository;
import com.ducquyet.restapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        var role=roleRepository.findByRole(EnumRole.ADMIN);
        if(role.isEmpty()) {
            var adminRole= Role.builder().id(1).role(EnumRole.ADMIN).build();
            roleRepository.save(adminRole);
            var adminUser= User.builder()
                    .username("ducquyetns2")
                    .id(1)
                    .password(passwordEncoder.encode("Anhquy28"))
                    .roles(Set.of(adminRole))
                    .build();
            userRepository.save(adminUser);
        }

    }
}
