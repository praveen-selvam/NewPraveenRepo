package com.autotrek.instantauto.web.dto.converters;

import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.web.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This is a class that will convert a User entity to a UserDto.
 *
 * @author Joe C. McPherson
 */
@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto convert(User source) {
        // We can do more if needed, but this works just with using the
        // provided library.
        UserDto dto = modelMapper.map(source, UserDto.class);
        return dto;
    }
}
