package com.ducquyet.restapi.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Entity(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "users")
//    @JoinTable(
//            name="users_roles",
//            joinColumns = @JoinColumn(name="role_id"),
//            inverseJoinColumns = @JoinColumn(name="user_id")
//    )
    private Set<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().toString())
        ).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
