package com.autotrek.instantauto.repositories;

import com.autotrek.instantauto.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author praveens
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findRoleByRoleNameAndCustomer(String roleName, String customer);
}
