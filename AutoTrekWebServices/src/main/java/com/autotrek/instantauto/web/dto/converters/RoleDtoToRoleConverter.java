package com.autotrek.instantauto.web.dto.converters;

import com.autotrek.instantauto.domain.Role;
import com.autotrek.instantauto.web.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author praveens
 */
@Component
public class RoleDtoToRoleConverter implements Converter<RoleDto, Role> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Role convert(RoleDto source) {
        Role role = modelMapper.map(source, Role.class);
        return role;
    }
}
