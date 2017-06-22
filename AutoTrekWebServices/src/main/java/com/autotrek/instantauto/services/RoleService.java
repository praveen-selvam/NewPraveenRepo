package com.autotrek.instantauto.services;

import com.autotrek.instantauto.domain.Role;
import java.util.List;

/**
 *
 * @author praveens
 */
public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleById(Long id);

    public Role addRole(Role role);

    public void updateRole(Role role);

    public void deleteRole(Long roleId);
}
