package com.ducquyet.restapi.repository;

import com.ducquyet.restapi.entity.EnumRole;
import com.ducquyet.restapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(EnumRole role);
    Set<Role> findByRoleIn(Set<EnumRole> role);
}
