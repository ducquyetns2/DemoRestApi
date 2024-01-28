package com.ducquyet.restapi.dto;

import com.ducquyet.restapi.entity.EnumRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
public class RegisterRequest {
    @NotNull
    @Size(max=200,message="Fullname is too large")
    private String fullName;
    @NotNull
    @Size(max=50,message="Username is too large")
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
    private Set<EnumRole> roles=Set.of(EnumRole.USER);
}