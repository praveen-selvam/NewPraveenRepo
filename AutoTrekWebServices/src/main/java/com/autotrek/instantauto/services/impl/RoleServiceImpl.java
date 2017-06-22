package com.autotrek.instantauto.services.impl;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.Role;
import com.autotrek.instantauto.repositories.RoleRepository;
import com.autotrek.instantauto.services.RoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author praveens
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        List<Role> list = roleRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        LOGGER.debug("Finding role by id: {}", id);
        return roleRepository.findOne(id);
    }

    @Override
    @Transactional
    public Role addRole(Role role) {

        if (!StringUtils.isEmpty(StringUtils.trimAllWhitespace(role.getRoleName()))) {
            Role exist = roleRepository.findRoleByRoleNameAndCustomer(role.getRoleName(), role.getCustomer());
            if (exist != null) {
                throw ApiExceptionBuilder.CONFLICT.build(String.format("A Role with the name '%s' for '%s' already exists.", role.getRoleName(), role.getCustomer()));
            }
        } else {
            throw ApiExceptionBuilder.BAD_REQUEST.build();
        }
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }
}
