package com.ducquyet.restapi.dto;

import com.ducquyet.restapi.entity.EnumRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Validated
public class AuthenticationRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
//    private Set<String> roles=Set.of(EnumRole.USER.toString());
}
