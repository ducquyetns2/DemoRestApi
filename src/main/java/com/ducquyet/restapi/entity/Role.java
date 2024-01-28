package com.ducquyet.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(value= EnumType.STRING)
    private EnumRole role;
    @ManyToMany(mappedBy = "roles")
//    @ManyToMany
//    @JoinTable(
//            name="users_roles",
//            joinColumns = @JoinColumn(name="role_id"),
//            inverseJoinColumns = @JoinColumn(name="user_id")
//    )
    @JsonIgnore
    private List<User> users;
}
