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
public class RoleToRoleDtoConverter implements Converter<Role, RoleDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto convert(Role source) {
        RoleDto dto = modelMapper.map(source, RoleDto.class);
        return dto;

    }

}
