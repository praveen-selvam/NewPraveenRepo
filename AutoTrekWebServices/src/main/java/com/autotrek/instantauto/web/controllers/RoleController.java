package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.Role;
import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.services.RoleService;
import com.autotrek.instantauto.web.dto.RoleDto;
import com.autotrek.instantauto.web.dto.converters.RoleDtoToRoleConverter;
import com.autotrek.instantauto.web.dto.converters.RoleToRoleDtoConverter;
import com.autotrek.instantauto.web.util.SecurityContextHelper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author praveens
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleToRoleDtoConverter roleToRoleDtoConverter;

    @Autowired
    private RoleDtoToRoleConverter roleDtoToRoleConverter;

    @Autowired
    private SecurityContextHelper securityContextHelper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        ArrayList<RoleDto> ret = new ArrayList<>();

        roles.stream().map((role) -> {
            RoleDto dto = roleToRoleDtoConverter.convert(role);
            return dto;
        }).forEachOrdered((dto) -> {
            ret.add(dto);
        });

        return ret;
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public RoleDto getRoleInfo(@PathVariable Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if (role != null) {
            RoleDto dto = roleToRoleDtoConverter.convert(role);
            return dto;
        } else {
            throw ApiExceptionBuilder.NOT_FOUND.build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public RoleDto addRole(@RequestBody RoleDto role) {

        User caller = securityContextHelper.getCallingUser();
        Role rol = roleDtoToRoleConverter.convert(role);
        rol.setCreatedBy(caller);

        Role savedRole = roleService.addRole(rol);
        return roleToRoleDtoConverter.convert(savedRole);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{roleId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateRole(@RequestBody RoleDto roleDto, @PathVariable Long roleId) {

        User caller = securityContextHelper.getCallingUser();
        Role role = roleDtoToRoleConverter.convert(roleDto);
        role.setCreatedBy(caller);
        role.setModifiedBy(caller);
        role.setId(roleId);
        roleService.updateRole(role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{roleId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
    }
}
